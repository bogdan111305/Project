package com.example.AccountingSystem.kafka.service;

import com.example.AccountingSystem.kafka.message.SalaryMessage;
import com.example.AccountingSystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReportConsumerService implements ConsumerService<SalaryMessage> {

    public static final Logger LOG = LoggerFactory.getLogger(ReportConsumerService.class);

    private final UserService userService;

    @Autowired
    public ReportConsumerService(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "${kafka.topic.consumer}", groupId = "consumer")
    public void receive(@Payload SalaryMessage salaryMessage) {
        LOG.info("message received: {}", salaryMessage.getSalary() + "for " + salaryMessage.getUsername());
        userService.updateUserSalary(salaryMessage);
    }
}
