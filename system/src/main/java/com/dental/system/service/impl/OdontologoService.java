package com.dental.system.service.impl;


import com.dental.system.entities.Odontologo;
import com.dental.system.exception.OdontologoException;
import com.dental.system.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private DireccionService direccionService;

    public Odontologo guardar(Odontologo odontologo){
        Optional<Odontologo> odontologoOptional = odontologoRepository.findByMatricula(odontologo.matricula);
        if(odontologoOptional.isPresent())
            new OdontologoException("Conflicto: Ya existe un odontologo con ese ID: "+odontologo.id);
        return odontologoRepository.save(odontologo);
    }

    public Odontologo modificar(Odontologo odontologo){
        return odontologoRepository.save(odontologo.toBuilder()
                .nombre(odontologo.nombre)
                .apellido(odontologo.apellido)
                .direccion(odontologo.direccion)
                .build());
    }
    public Odontologo budcarPorId(String id) throws OdontologoException {
        //orElseThrow(() ->  new OdontologoException("No existe Odontologo para el ID: "+id));
        return odontologoRepository.findById(id).get();
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }
    public void eliminarPorId(String id){
        odontologoRepository.deleteById(id);
    }

    public boolean exiteOdotologo( String id){
      return   odontologoRepository.existsById(id);
    }
}
