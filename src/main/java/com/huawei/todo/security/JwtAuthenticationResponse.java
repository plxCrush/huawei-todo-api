package com.huawei.todo.security;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    Long id;

    String username;

    String name;

    String token;

    public JwtAuthenticationResponse(JwtUser user, String token) {

        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.token = token;
    }
}