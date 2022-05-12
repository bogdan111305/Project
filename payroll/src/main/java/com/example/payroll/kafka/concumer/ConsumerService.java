package com.example.payroll.kafka.concumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public interface ConsumerService<T> {
    void consumptionMessage(T message);
}
