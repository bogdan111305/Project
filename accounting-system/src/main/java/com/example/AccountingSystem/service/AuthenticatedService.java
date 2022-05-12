package com.example.AccountingSystem.service;

import com.example.AccountingSystem.payload.request.LoginRequest;
import com.example.AccountingSystem.payload.response.JWTTokenSuccessResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticatedService {
    JWTTokenSuccessResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest);
}
