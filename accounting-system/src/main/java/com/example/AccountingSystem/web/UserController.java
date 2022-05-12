package com.example.AccountingSystem.web;

import com.example.AccountingSystem.payload.request.dto.UserDTO;
import com.example.AccountingSystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public UserDTO getUserProfile(Principal principal){
        return userService.getCurrentUser(principal);
    }

    @PostMapping("/update")
    public UserDTO updateUser(@Valid @RequestBody UserDTO userDTO, Principal principal){
        return userService.updateUser(userDTO, principal);
    }

    @PostMapping("/admin/delete/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("userId")Long userId){
        userService.deleteUser(userId);
    }
}
