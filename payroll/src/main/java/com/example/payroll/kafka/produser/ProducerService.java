package com.example.payroll.kafka.produser;

public interface ProducerService<T> {
    void send(T t);
}
