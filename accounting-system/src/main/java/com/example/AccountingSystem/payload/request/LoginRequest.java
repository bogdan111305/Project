package com.example.AccountingSystem.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginRequest {

    @NotEmpty(message = "Username not can empty")
    private String username;
    @NotEmpty(message = "Password not can empty")
    private String password;
}
