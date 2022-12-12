package com.abdelhakimrafik.accountService.queries.dtos;

import com.abdelhakimrafik.accountService.commonapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AccountResponseDTO {
    private String id;
    private double balance;
    private AccountStatus status;
}
