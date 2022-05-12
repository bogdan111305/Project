package com.example.AccountingSystem.kafka.service;

import com.example.AccountingSystem.kafka.message.ReportMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReportProducerService implements ProducerService<ReportMessage>{

    public static final Logger LOG = LoggerFactory.getLogger(ReportProducerService.class);

    @Value("${kafka.topic.producer}")
    private String topic;

    private final KafkaTemplate<String, ReportMessage> kafkaTemplate;

    @Autowired
    public ReportProducerService(KafkaTemplate<String, ReportMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(ReportMessage reportMessage) {
        kafkaTemplate.send(topic, reportMessage);
    }
}
