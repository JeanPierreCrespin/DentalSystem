package com.dental.system.service.impl;

import com.dental.system.enums.Rol;
import com.dental.system.entities.Usuario;
import com.dental.system.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationService implements UserDetailsService {



    @Autowired
    private UsuarioRepository usuarioRepository;



    @Override
    public UserDetails loadUserByUsername(String userName) throws

            UsernameNotFoundException {

        Optional<Usuario> user = usuarioRepository.getUserByUsername(userName);



        Set<GrantedAuthority> autorizaciones = new HashSet<>();

        GrantedAuthority autorizacion = null;



        //for (String rol : user.get().roles) {

            autorizacion = new SimpleGrantedAuthority("ROLE_" +user.get().rol.toString());

            autorizaciones.add(autorizacion);

       // }

        org.springframework.security.core.userdetails.User userDetail = new

                org.springframework.security.core.userdetails.User( user.get().username, user.get().password,

                true, true, true,true,

                autorizaciones);



        return userDetail;

    }





}