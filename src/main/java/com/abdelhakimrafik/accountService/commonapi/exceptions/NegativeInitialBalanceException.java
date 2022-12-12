package com.abdelhakimrafik.accountService.commonapi.exceptions;

public class NegativeInitialBalanceException extends RequestException {
    public NegativeInitialBalanceException(String negative_initial_balance) {
        super(negative_initial_balance);
    }
}
