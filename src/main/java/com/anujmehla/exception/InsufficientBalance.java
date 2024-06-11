package com.anujmehla.exception;

public class InsufficientBalance extends Exception {
    public InsufficientBalance(String errorMessage) {
        super(errorMessage);
    }
}
