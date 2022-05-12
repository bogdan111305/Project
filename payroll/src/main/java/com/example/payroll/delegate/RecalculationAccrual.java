package com.example.payroll.delegate;

import com.example.payroll.service.CalculationAccrualService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecalculationAccrual implements JavaDelegate {

    private final CalculationAccrualService calculationAccrualService;

    @Autowired
    public RecalculationAccrual(CalculationAccrualService calculationAccrualService) {
        this.calculationAccrualService = calculationAccrualService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String project = (String) delegateExecution.getVariable("project");
        Byte numberOfHours = (Byte) delegateExecution.getVariable("numberOfHours");

        try{
            int payment = calculationAccrualService.calculation(project, numberOfHours);
            delegateExecution.setVariable("payment", payment);
        } catch (Exception e){
            throw new BpmnError("paymentError");
        }
    }
}