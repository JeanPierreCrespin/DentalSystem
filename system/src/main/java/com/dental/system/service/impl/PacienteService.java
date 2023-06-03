package com.dental.system.service.impl;

import com.dental.system.model.Paciente;
import com.dental.system.service.IAciones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IAciones<Paciente> {
    @Override
    public Paciente agregar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente modificar(Paciente paciente) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public List<Paciente> listar() {
        return null;
    }
}
