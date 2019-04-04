package com.huawei.todo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huawei.todo.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
public class JwtUser implements UserDetails {

    @JsonIgnore
    long id;

    String username;

    String name;

    @JsonIgnore
    String password;

    boolean enabled;

    public JwtUser(User user) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

       return null;
    }
}