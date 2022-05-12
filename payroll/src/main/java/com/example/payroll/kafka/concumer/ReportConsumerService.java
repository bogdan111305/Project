package com.example.payroll.kafka.concumer;

import com.example.payroll.kafka.message.ReportMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ReportConsumerService implements ConsumerService<ReportMessage> {

    private final ProcessService processService;

    @Value(value = "accrual-process")
    private String correlationId;

    @Autowired
    public ReportConsumerService(@Qualifier("reportProcessService") ProcessService processService) {
        this.processService = processService;
    }

    @KafkaListener(topics = "${kafka.topic.consumer}", groupId = "groupId")
    public void consumptionMessage(@Payload ReportMessage message){
        processService.startProcess(message, correlationId);
    }
}
