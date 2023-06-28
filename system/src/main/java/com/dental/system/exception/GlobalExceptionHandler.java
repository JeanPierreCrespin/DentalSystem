package com.dental.system.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private GlobalResponseService  globalResponseService;


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OdontologoException.class)
    public ResponseEntity<?> handleException(OdontologoException e) {
        return new ResponseEntity<>(globalResponseService.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PacienteException.class)
    public ResponseEntity<?> handleException(PacienteException e) {
        return new ResponseEntity<>(globalResponseService.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TurnoException.class)
    public ResponseEntity<?> handleException(TurnoException e) {
        return new ResponseEntity<>(globalResponseService.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(JWTException.class)
    public ResponseEntity<?> handleException(JWTException e) {
        return new ResponseEntity<>(globalResponseService.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<?> handleException(UsuarioException e) {
        return new ResponseEntity<>(globalResponseService.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
