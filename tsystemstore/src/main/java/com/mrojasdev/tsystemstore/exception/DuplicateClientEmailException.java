package com.mrojasdev.tsystemstore.exception;

public class DuplicateClientEmailException extends RuntimeException {
    public DuplicateClientEmailException(String message){
        super(message);
    }
}
