package com.example.payroll.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryMessage {
    private String username;
    private int salary;
}
