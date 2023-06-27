package com.dental.system.repository;

import com.dental.system.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("from Usuario u where u.username =:username")
    Optional<Usuario> getUserByUsername(@Param("username") String username);
}
