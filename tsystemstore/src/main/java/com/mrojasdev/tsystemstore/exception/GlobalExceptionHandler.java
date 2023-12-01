package com.mrojasdev.tsystemstore.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    Handles the exceptions produced when a requested entity can't be found in the database
     */
    @ExceptionHandler(value = {
            ClientNotFoundException.class, AddressNotFoundException.class, OrderNotFoundException.class,
            ProductNotFoundException.class
    })
    public final ResponseEntity<Object> handleEntityNotFound(ClientNotFoundException ex, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        return handleExceptionInternal(ex, details, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
