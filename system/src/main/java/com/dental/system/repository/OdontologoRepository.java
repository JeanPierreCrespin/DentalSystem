package com.dental.system.repository;

import com.dental.system.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, String> {
    public Optional<Odontologo> findByMatricula(String matricula);
}
