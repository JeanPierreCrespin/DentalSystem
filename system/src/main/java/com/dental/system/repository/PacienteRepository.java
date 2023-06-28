package com.dental.system.repository;

import com.dental.system.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, String> {
    public Optional<Paciente> findByDni(String dni);
    @Query("select p from Paciente p join p.turnos t where t.estado =  true and p.id =:id")
    public Optional<Paciente> buscarPacienteConTurnoActivo(String id);

    @Query("select p from Paciente p where p.estado = true")
    public List<Paciente> listar();

}
