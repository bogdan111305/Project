package com.example.AccountingSystem.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class JWTTokenSuccessResponse {
    private boolean success;
    private String token;
}