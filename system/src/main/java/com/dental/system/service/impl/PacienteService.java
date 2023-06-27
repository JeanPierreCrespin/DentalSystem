package com.dental.system.service.impl;

import com.dental.system.entities.Paciente;
import com.dental.system.exception.PacienteException;
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
        Optional<Paciente> pacienteOptional = pacienteRepository.findByDni(paciente.dni);
        if(pacienteOptional.isPresent())
            new PacienteException("Conficto: Ya existe el Pacinete con el DNI: "+ paciente.dni);
        return pacienteRepository.save(paciente);
    }

    public Paciente modificar(Paciente paciente){
        return pacienteRepository.save((paciente.toBuilder()
                .nombre(paciente.nombre)
                .apellido(paciente.apellido)
                .fechaAlta(paciente.fechaAlta)
                .dni(paciente.dni)
                .direccion(paciente.direccion)
                .build()));
    }

    public Paciente buscarPorId(String id) throws PacienteException {
        return pacienteRepository.findById(id).orElseThrow(() -> new PacienteException("No existe paciente con el ID: "+id));
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    public void eliminar(String id){
        pacienteRepository.deleteById(id);
    }

    public Boolean existePaciente(String id){
        return  pacienteRepository.existsById(id);
    }
}
