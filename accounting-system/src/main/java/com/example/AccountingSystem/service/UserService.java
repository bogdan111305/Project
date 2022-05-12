package com.example.AccountingSystem.service;

import com.example.AccountingSystem.entity.User;
import com.example.AccountingSystem.kafka.message.SalaryMessage;
import com.example.AccountingSystem.payload.request.SignupRequest;
import com.example.AccountingSystem.payload.request.dto.UserDTO;
import org.springframework.validation.BindingResult;

import java.security.Principal;

public interface UserService {
    UserDTO creatUser(SignupRequest userDTO);

    UserDTO updateUser(UserDTO userDTO, Principal principal);

    UserDTO getCurrentUser(Principal principal);

    User getUserById(Long userId);

    void updateUserSalary(SalaryMessage salaryMessage);

    void deleteUser(Long userId);
}
