package com.dental.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsuarioException extends RuntimeException{
    public UsuarioException(String message) {
        super(message);
    }
}
