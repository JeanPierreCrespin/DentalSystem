package com.dental.system.service.impl;

import com.dental.system.entities.Odontologo;
import com.dental.system.entities.Paciente;
import com.dental.system.exception.OdontologoException;
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
        validarNombreApellidoDNI(paciente);
        paciente.estado = true;
        Optional<Paciente> pacienteOptional = pacienteRepository.findByDni(paciente.dni);
        if(pacienteOptional.isPresent())
            throw new PacienteException("Conficto: Ya existe el Pacinete con el DNI: "+ paciente.dni+".");
        return pacienteRepository.save(paciente);
    }

    private void validarNombreApellidoDNI(Paciente paciente) {
        if(paciente.nombre == null || paciente.nombre.isEmpty())
            throw new OdontologoException("El nombre del paciente no puede ser null o vacio.");
        if(paciente.apellido == null || paciente.apellido.isEmpty())
            throw new OdontologoException("El apellido del paciente no puede ser null o vacio.");
        if(paciente.dni == null || paciente.dni.isEmpty())
            throw new OdontologoException("El DNI del Paciente no puede ser null o vacio.");
    }
    public Paciente modificar(Paciente paciente){
        validarNombreApellidoDNI(paciente);
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
            throw  new PacienteException("El id Paciente no puede ser vacio o null.");
        return pacienteRepository.findById(id).orElseThrow(() ->  new PacienteException("No existe paciente con el ID: "+id+"."));
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.listar();
    }

    public void eliminar(String id){
        Optional<Paciente> pacienteOp = pacienteRepository.buscarPacienteConTurnoActivo(id);
        if(pacienteOp.isPresent())
            throw new TurnoException("No se puede eliminar el paciente con el ID: "+id+" porque tiene un turno activo asociado.");
        Paciente paciente = buscarPorId(id);
        paciente.estado = false;
        pacienteRepository.save(paciente);
    }

    public Boolean existePaciente(String id){
        return  pacienteRepository.existsById(id);
    }
}
