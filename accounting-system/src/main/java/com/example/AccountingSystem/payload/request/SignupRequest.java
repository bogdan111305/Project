package com.example.AccountingSystem.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter @Getter
public class SignupRequest {

    @NotEmpty(message = "Please enter your username")
    private String username;
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String firstName;
    @NotEmpty(message = "Please enter your lastname")
    private String lastName;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    @Size(min = 6)
    private String confirmPassword;

}
