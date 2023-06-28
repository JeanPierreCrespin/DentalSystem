package com.dental.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OdontologoException extends RuntimeException{
    public OdontologoException(String message) {
        super(message);
    }
}
