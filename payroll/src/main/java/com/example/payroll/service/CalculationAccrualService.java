package com.example.payroll.service;

public interface CalculationAccrualService {
    int calculation(String project, Byte numberOfHours) throws Exception;
}
