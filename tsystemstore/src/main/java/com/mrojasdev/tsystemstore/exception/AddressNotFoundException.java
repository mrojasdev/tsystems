package com.mrojasdev.tsystemstore.exception;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String message){
        super(message);
    }
}