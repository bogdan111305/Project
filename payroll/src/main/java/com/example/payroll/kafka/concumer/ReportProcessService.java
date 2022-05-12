package com.example.payroll.kafka.concumer;

import com.example.payroll.exception.ValidResponseBodyException;
import com.example.payroll.kafka.message.ReportMessage;
import com.example.payroll.kafka.util.VariablesUtil;
import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReportProcessService implements ProcessService<ReportMessage> {

    public static final Logger LOG = LoggerFactory.getLogger(ReportProcessService.class);

    private final RuntimeService runtimeService;
    private final LocalValidatorFactoryBean validatorFactory;

    @Autowired
    public ReportProcessService(RuntimeService runtimeService, LocalValidatorFactoryBean validatorFactory) {
        this.runtimeService = runtimeService;
        this.validatorFactory = validatorFactory;
    }

    public void startProcess(ReportMessage message, String correlationId) {
        try {
            if (message != null) {

                final Set<ConstraintViolation<ReportMessage>> violations = validatorFactory.getValidator().validate(message);

                if (!violations.isEmpty()){
                    List<String> errors = new ArrayList<>();
                    for (ConstraintViolation msg: violations) {
                        errors.add(msg.getMessage());
                    }
                    throw new ValidResponseBodyException("Validation failed", errors);
                } else{
                    runtimeService.createProcessInstanceByKey(correlationId)
                            .setVariables(VariablesUtil.toVariableMap(message))
                            .executeWithVariablesInReturn();
                }
            }

            LOG.info("Correlation key used: {}", correlationId);
        } catch (MismatchingMessageCorrelationException e) {
            LOG.error("Issue when correlating the message: {}", e.getMessage());
        } catch (Exception e) {
            LOG.error("Unknown issue occurred", e);
        }
    }
}