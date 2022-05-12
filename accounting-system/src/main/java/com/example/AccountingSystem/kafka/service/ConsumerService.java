package com.example.AccountingSystem.kafka.service;

public interface ConsumerService<T>{
    void receive(T t);
}
