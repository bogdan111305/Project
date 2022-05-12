package com.example.AccountingSystem.validation;

import com.example.AccountingSystem.exceptions.ValidResponseBodyException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseErrorValidation {
    public void mapValidationsService(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(x -> x.getDefaultMessage())
                    .collect(Collectors.toList());

            throw new ValidResponseBodyException("Method Argument Not Valid", errors);
        }
    }
}