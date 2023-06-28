package com.dental.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PacienteException extends RuntimeException {
    public PacienteException(String message) {
        super(message);
    }
}
