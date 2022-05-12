package com.example.payroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationAccrualServiceImpl implements CalculationAccrualService{

    private final RateStorageService rateStorageService;

    @Autowired
    public CalculationAccrualServiceImpl(RateStorageService rateStorageService) {
        this.rateStorageService = rateStorageService;
    }

    @Override
    public int calculation(String project, Byte numberOfHours) throws Exception {
        int rateAccrual = rateStorageService.getRateStorageByProject(project).getRate();

        return numberOfHours * rateAccrual;
    }
}
