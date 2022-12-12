package com.abdelhakimrafik.accountService.commonapi.exceptions;

public abstract class RequestException extends Throwable {
    public RequestException(String message) {
        super(message);
    }
}
