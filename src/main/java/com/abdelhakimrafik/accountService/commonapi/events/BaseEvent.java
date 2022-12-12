package com.abdelhakimrafik.accountService.commonapi.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
public class BaseEvent<T> {
    @Getter
    private T id;
}
