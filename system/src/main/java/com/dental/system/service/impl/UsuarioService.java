package com.dental.system.service.impl;

import com.dental.system.entities.Usuario;
import com.dental.system.entities.dto.AuthenticationRequest;
import com.dental.system.entities.dto.AuthenticationResponse;
import com.dental.system.exception.UsuarioException;
import com.dental.system.repository.UsuarioRepository;
import com.dental.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationResponse guardar(Usuario usuario) throws UsuarioException {
        validarUsernamePassword(usuario);

        usuario.password = passwordEncoder.encode(usuario.password);
        Usuario user = usuarioRepository.save(usuario);
        return new AuthenticationResponse(jwtUtil.generateToken(authenticationService.loadUserByUsername(user.username)));
    }

    private void validarUsernamePassword(Usuario usuario) throws UsuarioException {
        if(usuario.username.isEmpty() || usuario.username.length() <= 0)
            throw  new UsuarioException("El username no puede ser nulo o vacio.");

        if(usuario.password.isEmpty() || usuario.password.length() <= 0)
            throw  new UsuarioException("Debe ingresar una contraseña.");
    }

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) throws UsuarioException {
        validarUsernamePassword(new Usuario().builder()
                .username(authenticationRequest.username)
                .password(authenticationRequest.password)
                .build()
        );

       // Usuario usuario1 = usuarioRepository.getUserByUsername(usuario.username).orElseThrow(()-> new UsuarioException("No existe usuario con el username: "+ usuario.username));

        UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.username);
        if(userDetails == null)
            new UsuarioException("No se encontro ningun usuario asociado al uaername:  "+authenticationRequest.username);

        if(!passwordEncoder.matches(authenticationRequest.password, userDetails.getPassword()))
            new UsuarioException("Contrseña incorrecta");

        return new AuthenticationResponse(jwtUtil.generateToken(userDetails));
    }

}
