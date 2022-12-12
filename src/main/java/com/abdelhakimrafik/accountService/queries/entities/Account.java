package com.abdelhakimrafik.accountService.queries.entities;

import com.abdelhakimrafik.accountService.commonapi.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id
    private String id;
    private String currency;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> transactions;
}
