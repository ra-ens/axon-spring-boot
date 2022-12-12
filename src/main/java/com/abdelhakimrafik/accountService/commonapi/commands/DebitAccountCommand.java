package com.abdelhakimrafik.accountService.commonapi.commands;

import lombok.Getter;

@Getter
public class DebitAccountCommand extends BaseCommand<String> {
    private String currency;
    private double amount;

    public DebitAccountCommand(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
