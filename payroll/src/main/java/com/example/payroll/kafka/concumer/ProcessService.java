package com.example.payroll.kafka.concumer;

public interface ProcessService<T> {
    void startProcess(T message, String correlationId);
}
