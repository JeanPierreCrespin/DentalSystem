package com.dental.system.service.impl;

import com.dental.system.entities.Paciente;
import com.dental.system.exception.PacienteException;
import com.dental.system.exception.TurnoException;
import com.dental.system.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardar(Paciente paciente){
        paciente.estado = true;
        Optional<Paciente> pacienteOptional = pacienteRepository.findByDni(paciente.dni);
        if(pacienteOptional.isPresent())
            throw new PacienteException("Conficto: Ya existe el Pacinete con el DNI: "+ paciente.dni);
        return pacienteRepository.save(paciente);
    }

    public Paciente modificar(Paciente paciente){
        Paciente pacienteR = buscarPorId(paciente.id).toBuilder()
                .nombre(paciente.nombre)
                .apellido(paciente.apellido)
                .fechaAlta(paciente.fechaAlta)
                .dni(paciente.dni)
                .direccion(paciente.direccion)
                .build();
        return pacienteRepository.save(pacienteR);
    }

    public Paciente buscarPorId(String id) throws PacienteException {
        if(id == null || id.isEmpty())
            throw  new PacienteException("El id Paciente no puede ser vacio o null ");
        return pacienteRepository.findById(id).orElseThrow(() ->  new PacienteException("No existe paciente con el ID: "+id));
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.listar();
    }

    public void eliminar(String id){
        Optional<Paciente> pacienteOp = pacienteRepository.buscarPacienteConTurnoActivo(id);
        if(pacienteOp.isPresent())
            throw new TurnoException("No se puede eliminar el paciente con el ID: "+id+" porque tiene un turno activo asociado.");
        Paciente paciente = pacienteOp.get();
        paciente.estado = false;
        pacienteRepository.save(paciente);
    }

    public Boolean existePaciente(String id){
        return  pacienteRepository.existsById(id);
    }
}
