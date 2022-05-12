package com.example.AccountingSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidResponseBodyException extends RuntimeException{
    private List<String> errors;
    public ValidResponseBodyException(String message, List<String> errors){
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
