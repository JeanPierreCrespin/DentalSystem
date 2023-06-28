package com.dental.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TurnoException extends RuntimeException{
    public TurnoException(String message) {
        super(message);
    }
}
