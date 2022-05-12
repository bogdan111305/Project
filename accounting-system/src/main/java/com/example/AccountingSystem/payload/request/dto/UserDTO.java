package com.example.AccountingSystem.payload.request.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    private Long id;
    @NotEmpty(message = "Username not can empty")
    @Size(min = 8)
    private String username;
    @NotEmpty(message = "FirstName not can empty")
    @Size(min = 3, max = 15)
    private String firstName;
    @NotEmpty(message = "LastName not can empty")
    @Size(min = 3, max = 15)
    private String lastName;
    @NotEmpty(message = "Email not can empty")
    @Size(min = 8)
    private String email;
}
