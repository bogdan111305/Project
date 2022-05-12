package com.example.AccountingSystem.kafka.service;

public interface ProducerService<T> {
    void send(T t);
}
