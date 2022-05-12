package com.example.payroll.delegate;

import com.example.payroll.entity.Accrual;
import com.example.payroll.kafka.util.VariablesUtil;
import com.example.payroll.service.AccrualService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveChanges implements JavaDelegate {

    private final AccrualService accrualService;

    @Autowired
    public SaveChanges(AccrualService accrualService) {
        this.accrualService = accrualService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String serviceTask = (String) delegateExecution.getVariable("serviceTask");
        int payment = (int) delegateExecution.getVariable("payment");

        Accrual accrual = new Accrual();
        VariablesUtil.ToObject(delegateExecution.getVariables(), accrual);
        accrual.setPayment(payment);

        if (serviceTask.equals("create") || serviceTask.equals("update")){
            accrualService.createOrUpdate(accrual);
        } else {
            accrualService.delete(accrual);
        }
    }
}
