package com.example.payroll.service;

import com.example.payroll.entity.Accrual;
import com.example.payroll.exception.EntityNotFoundException;
import com.example.payroll.repository.AccrualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccrualServiceImpl implements AccrualService {

    private final AccrualRepository accrualRepository;

    @Autowired
    public AccrualServiceImpl(AccrualRepository accrualRepository) {
        this.accrualRepository = accrualRepository;
    }

    @Override
    public Accrual createOrUpdate(Accrual accrual) {
        return accrualRepository.save(accrual);
    }

    @Override
    public void delete(Accrual accrual) {
        accrualRepository.delete(accrual);
    }

    @Override
    public Accrual getReportMessage(Long reportMessageId, String username) {
        return accrualRepository.findAccrualByIdAndUsername(reportMessageId, username).orElseThrow(() -> new EntityNotFoundException("Report message not found"));
    }

    @Override
    public List<Accrual> getAllAccrualForMonth(String username) {
        LocalDate datePreviousMonth = LocalDate.now().withDayOfMonth(1);
        return accrualRepository.findAccrualByUsernameAndAndDateReportAfter(username, datePreviousMonth.atStartOfDay());
    }
}
