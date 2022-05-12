package com.example.AccountingSystem.web;

import com.example.AccountingSystem.payload.request.LoginRequest;
import com.example.AccountingSystem.payload.request.SignupRequest;
import com.example.AccountingSystem.payload.request.dto.UserDTO;
import com.example.AccountingSystem.payload.response.JWTTokenSuccessResponse;
import com.example.AccountingSystem.service.AuthenticatedServiceImpl;
import com.example.AccountingSystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    public static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthenticatedServiceImpl authenticatedService;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(UserService userService, AuthenticatedServiceImpl authenticatedService, ModelMapper modelMapper){
        this.userService = userService;
        this.authenticatedService = authenticatedService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public JWTTokenSuccessResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        return authenticatedService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody SignupRequest signupRequest){
        return userService.creatUser(signupRequest);
    }

}
