package com.dental.system.service.impl;

import com.dental.system.model.Odontologo;
import com.dental.system.service.IAciones;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IAciones<Odontologo> {
    @Override
    public Odontologo agregar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo modificar(Odontologo odontologo) {
        return null;
    }

    @Override
    public void eliminar(String id) {

    }

    @Override
    public List<Odontologo> listar() {
        return null;
    }
}
