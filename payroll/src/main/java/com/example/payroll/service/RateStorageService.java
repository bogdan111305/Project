package com.example.payroll.service;

import com.example.payroll.entity.RateStorage;

public interface RateStorageService {
    RateStorage getRateStorageByProject(String project) throws Exception;

    RateStorage create(String project, Integer rate);
}
