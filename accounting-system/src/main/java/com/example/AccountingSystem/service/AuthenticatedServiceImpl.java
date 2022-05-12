package com.example.AccountingSystem.service;

import com.example.AccountingSystem.payload.request.LoginRequest;
import com.example.AccountingSystem.payload.response.JWTTokenSuccessResponse;
import com.example.AccountingSystem.security.JWTTokenProvider;
import com.example.AccountingSystem.security.SecurityConstants;
import com.example.AccountingSystem.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
public class AuthenticatedServiceImpl {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ResponseErrorValidation responseErrorValidation;

    @Autowired
    public AuthenticatedServiceImpl(JWTTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, ResponseErrorValidation responseErrorValidation){
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.responseErrorValidation = responseErrorValidation;
    }
    public JWTTokenSuccessResponse authenticateUser(@RequestBody @Valid LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return new JWTTokenSuccessResponse(true, token);
    }
}
