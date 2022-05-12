package com.example.payroll.service;

import com.example.payroll.entity.Accrual;

import java.util.List;

public interface AccrualService {
    Accrual createOrUpdate(Accrual accrual);
    void delete(Accrual accrual);
    Accrual getReportMessage(Long accrualId, String username);
    List<Accrual> getAllAccrualForMonth(String username);
}
