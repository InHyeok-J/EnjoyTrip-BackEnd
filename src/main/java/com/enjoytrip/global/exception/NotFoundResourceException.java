package com.enjoytrip.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundResourceException extends BusinessException{

    public NotFoundResourceException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

    public NotFoundResourceException(String message, int statusCode) {
        super(message, statusCode);
    }
}
