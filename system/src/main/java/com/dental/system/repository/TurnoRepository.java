package com.dental.system.repository;

import com.dental.system.entities.Odontologo;
import com.dental.system.entities.Paciente;
import com.dental.system.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, String> {


 public Optional<Turno> findByFechaYHoraAndAndOdontologo(LocalDateTime fechaYhora, Odontologo odontologo);

 public Optional<Turno> findByFechaYHoraAndOdontologoAndPaciente(LocalDateTime fechaYhora, Odontologo odontologo, Paciente paciente);
}
