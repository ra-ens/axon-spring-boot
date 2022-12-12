package com.abdelhakimrafik.accountService.commonapi.events;

import lombok.Getter;

@Getter
public class AccountDebitedEvent extends BaseEvent<String> {
    private String currency;
    private double amount;

    public AccountDebitedEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
