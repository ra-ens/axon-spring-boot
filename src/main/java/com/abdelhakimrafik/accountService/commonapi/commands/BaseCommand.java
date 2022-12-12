package com.abdelhakimrafik.accountService.commonapi.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
public class BaseCommand<T> {
    @TargetAggregateIdentifier
    private T id;
}
