package com.dental.system.controller;

import com.dental.system.entities.Usuario;
import com.dental.system.entities.dto.AuthenticationRequest;
import com.dental.system.entities.dto.AuthenticationResponse;
import com.dental.system.exception.UsuarioException;
import com.dental.system.service.impl.AuthenticationService;
import com.dental.system.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/registrar")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario) throws UsuarioException {
        return new ResponseEntity<>(usuarioService.guardar(usuario),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        return  new ResponseEntity<>(usuarioService.authentication(authenticationRequest),HttpStatus.OK);
    }
}
