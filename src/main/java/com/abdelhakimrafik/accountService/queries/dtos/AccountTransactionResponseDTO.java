package com.abdelhakimrafik.accountService.queries.dtos;

import com.abdelhakimrafik.accountService.commonapi.enums.TransationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AccountTransactionResponseDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private TransationType type;
}
