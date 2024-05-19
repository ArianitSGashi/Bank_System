package com.springboot.banksystem.exceptions;

public class BankServiceException extends RuntimeException {
    public BankServiceException(String message) {
        super(message);
    }
}
