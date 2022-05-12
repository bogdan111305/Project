package com.example.AccountingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReportNotFoundException extends RuntimeException{
    public ReportNotFoundException(String message){
        super(message);
    }
}
