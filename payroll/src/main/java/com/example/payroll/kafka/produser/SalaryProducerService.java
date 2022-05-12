package com.example.payroll.kafka.produser;

import com.example.payroll.kafka.message.SalaryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SalaryProducerService implements ProducerService<SalaryMessage>{
    @Value("${kafka.topic.producer}")
    private String topic;

    private final KafkaTemplate<String, SalaryMessage> kafkaTemplate;

    @Autowired
    public SalaryProducerService(KafkaTemplate<String, SalaryMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(SalaryMessage salaryMessage) {

        kafkaTemplate.send(topic, salaryMessage);
    }
}
