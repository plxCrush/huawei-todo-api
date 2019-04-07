package com.huawei.todo.controllers;

import com.huawei.todo.controllers.exceptions.ApiErrorMessage;
import com.huawei.todo.controllers.exceptions.ApiException;
import com.huawei.todo.controllers.exceptions.ApiValidationException;
import com.huawei.todo.controllers.exceptions.AuthenticationException;
import com.huawei.todo.dtos.SignUpRequest;
import com.huawei.todo.models.User;
import com.huawei.todo.security.*;
import com.huawei.todo.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtAuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    public AuthController(JwtAuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity signup(@Valid @RequestBody SignUpRequest signUpRequest, Errors errors) throws ApiException {

        if (errors.hasErrors()) {
            throw new ApiValidationException(errors);
        }

        if (userService.get(signUpRequest.getUsername()) != null) {
            throw new ApiException(ApiErrorMessage.USERNAME_ALREADY_EXISTS);
        } else if (!signUpRequest.getPassword().equals(signUpRequest.getPasswordRepeat())) {
            throw new ApiException(ApiErrorMessage.PASSWORD_DOES_NOT_MATCH_REPEAT);
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.hashPassword(signUpRequest.getPassword());
        user.setName(signUpRequest.getName());
        user.setEnabled(true);
        userService.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok(new JwtAuthenticationResponse(user, token));
    }

    @GetMapping("refresh")
    public ResponseEntity refreshAndGetAuthenticationToken(HttpServletRequest request) {

        String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        String refreshedToken = jwtTokenUtil.refreshToken(token);
        return ResponseEntity.ok(new JwtAuthenticationResponse(user, refreshedToken));
    }

    @GetMapping("user")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {

        String token = request.getHeader("Authorization").substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity handleAuthenticationException(AuthenticationException e) {

        Map response = new HashMap<>();
        response.put("success", false);
        response.put("errorType", e.getClass());
        response.put("errorMessage", e.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) throws AuthenticationException {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (BadCredentialsException e) {
            throw new AuthenticationException(ApiErrorMessage.INVALID_USERNAME_OR_PASSWORD);
        }
    }

}
