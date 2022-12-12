package com.abdelhakimrafik.accountService.commonapi.exceptions;

public class NegativeAmountException extends RequestException {
    public NegativeAmountException(String negative_amount) {
        super(negative_amount);
    }
}
