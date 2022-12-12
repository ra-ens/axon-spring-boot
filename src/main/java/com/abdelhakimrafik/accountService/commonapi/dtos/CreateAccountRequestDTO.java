package com.abdelhakimrafik.accountService.commonapi.dtos;

import lombok.Getter;

@Getter
public class CreateAccountRequestDTO {
    private String currency;
    private double initialBalance;
}
