package com.dental.system.repository;

import com.dental.system.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, String> {

    @Query("select o from Odontologo o where o.matricula =:matricula")
    public Optional<Odontologo> findByMatricula(String matricula);

    @Query("select o from Odontologo o where o.id =:id and o.estado = true")
    public Optional<Odontologo> buscarOdontoloPorIdEstado(String id);
    @Query("select o from Odontologo o join o.turnos  t where o.id =:id and t.estado = true")
    public Optional<Odontologo> buscarOdontoloConTurnoActivo(String id);

    @Query("select o from Odontologo o where  o.estado = true")
    public List<Odontologo> listar();
}
