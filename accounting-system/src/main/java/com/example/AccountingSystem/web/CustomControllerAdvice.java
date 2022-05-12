package com.example.AccountingSystem.web;

import com.example.AccountingSystem.exceptions.ReportNotFoundException;
import com.example.AccountingSystem.exceptions.UserExistException;
import com.example.AccountingSystem.payload.response.ResponseError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleUserExist(UserExistException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        ResponseError responseError = new ResponseError(message, "Please check credentials");
        return responseError;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleUsernameNotFound(UsernameNotFoundException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        ResponseError responseError = new ResponseError(message, "Please check username");
        return responseError;
    }

    @ExceptionHandler(ReportNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleReportNotFoundException(ReportNotFoundException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        ResponseError responseError = new ResponseError(message, "Please check your identifier report");
        return responseError;
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest webRequest){
        ResponseError responseError = new ResponseError("Malformed JSON Request", ex.getMessage());
        return new ResponseEntity(responseError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ResponseError responseError = new ResponseError("Validation failed", "Please check credentials", errors);
        return new ResponseEntity<>(responseError, status);
    }

}