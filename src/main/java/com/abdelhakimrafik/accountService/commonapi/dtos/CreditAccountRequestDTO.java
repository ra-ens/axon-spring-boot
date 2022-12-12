package com.abdelhakimrafik.accountService.commonapi.dtos;

import lombok.Getter;

@Getter
public class CreditAccountRequestDTO {
    private String accountId;
    private String currency;
    private double amount;
}
