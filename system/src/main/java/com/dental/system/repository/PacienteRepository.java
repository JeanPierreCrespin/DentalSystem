package com.dental.system.repository;

import com.dental.system.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, String> {
    public Optional<Paciente> findByDni(String dni);
}
