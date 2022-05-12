package com.example.AccountingSystem.kafka.message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryMessage {
    private String username;
    private int salary;
}
