package com.example.payroll.delegate;

import com.example.payroll.entity.Accrual;
import com.example.payroll.kafka.message.ReportMessage;
import com.example.payroll.kafka.message.SalaryMessage;
import com.example.payroll.kafka.produser.ProducerService;
import com.example.payroll.service.AccrualService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendUpdatedSalary implements JavaDelegate {

    private final AccrualService accrualService;
    private final ProducerService<SalaryMessage> producerService;

    @Autowired
    public SendUpdatedSalary(AccrualService accrualService, ProducerService<SalaryMessage> producerService) {
        this.accrualService = accrualService;
        this.producerService = producerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String username = (String) delegateExecution.getVariable("username");

        List<Accrual> accruals = accrualService.getAllAccrualForMonth(username);

        int salary = 0;
        for (Accrual accrual: accruals) {
            salary += accrual.getPayment();
        }

        SalaryMessage salaryMessage = new SalaryMessage(username, salary);

        producerService.send(salaryMessage);
    }
}
