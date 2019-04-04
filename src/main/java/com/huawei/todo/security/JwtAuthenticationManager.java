package com.huawei.todo.security;

import com.huawei.todo.models.User;
import com.huawei.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = userService.get(username);

        if (user == null || !user.verifyPassword(password)) {
            throw new BadCredentialsException(null);
        }

        if (!user.isEnabled()) {
            throw new DisabledException(null);
        }

        return authentication;
    }
}
