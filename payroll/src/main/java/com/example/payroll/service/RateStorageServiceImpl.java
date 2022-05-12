package com.example.payroll.service;

import com.example.payroll.entity.RateStorage;
import com.example.payroll.exception.EntityNotFoundException;
import com.example.payroll.repository.RateStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateStorageServiceImpl implements RateStorageService{

    private final RateStorageRepository rateStorageRepository;

    @Autowired
    public RateStorageServiceImpl(RateStorageRepository rateStorageRepository) {
        this.rateStorageRepository = rateStorageRepository;
    }

    public RateStorage create(String project, Integer rate){
        RateStorage rateStorage = new RateStorage();
        rateStorage.setProject(project);
        rateStorage.setRate(rate);

        return rateStorageRepository.save(rateStorage);
    }

    @Override
    public RateStorage getRateStorageByProject(String project) throws Exception{
        return rateStorageRepository.findRateStorageByProject(project).orElseThrow(() -> new EntityNotFoundException("Rate not found"));
    }
}
