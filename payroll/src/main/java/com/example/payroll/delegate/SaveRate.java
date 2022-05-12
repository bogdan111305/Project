package com.example.payroll.delegate;

import com.example.payroll.service.RateStorageService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveRate implements JavaDelegate {

    private final RateStorageService rateStorageService;

    @Autowired
    public SaveRate(RateStorageService rateStorageService) {
        this.rateStorageService = rateStorageService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if(delegateExecution.hasVariable("rate")){
            String project = (String) delegateExecution.getVariable("project");
            Long rate = (Long) delegateExecution.getVariable("rate");

            try {
                Integer rateInt = rate == null ? null : Math.toIntExact(rate);
                rateStorageService.create(project, rateInt);
            } catch (Exception exception){
                throw new BpmnError("paymentError");
            }

            delegateExecution.setVariable("isCreatedRate", true);
        } else {
            delegateExecution.setVariable("isCreatedRate", false);
        }
    }
}