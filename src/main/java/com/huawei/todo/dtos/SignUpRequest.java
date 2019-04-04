package com.huawei.todo.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Name cannot be blank.")
    String name;

    @NotBlank(message = "Username cannot be blank.")
    String username;

    @NotBlank(message = "Password cannot be blank.")
    String password;

    @NotBlank(message = "Password repeat cannot be blank.")
    String passwordRepeat;
}
