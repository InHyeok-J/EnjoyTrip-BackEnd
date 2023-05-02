package com.enjoytrip.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    int statusCode;

    public BusinessException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
