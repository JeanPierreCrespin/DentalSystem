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
        odontologo.estado = true;
        Optional<Odontologo> odontologoOptional = odontologoRepository.findByMatricula(odontologo.matricula);
        if(odontologoOptional.isPresent())
            throw new OdontologoException("Conflicto: Ya existe un odontologo con la Matricola: "+odontologo.matricula);

        return odontologoRepository.save(odontologo);
    }

    public Odontologo modificar(Odontologo odontologo){
        Odontologo odontologoR = buscarPorId(odontologo.id).toBuilder()
                .nombre(odontologo.nombre)
                .apellido(odontologo.apellido)
                .direccion(odontologo.direccion)
                .build();
        return odontologoRepository.save(odontologoR);
    }
    public Odontologo buscarPorId(String id) throws OdontologoException {
        if(id == null || id.isEmpty())
            throw new OdontologoException("El id Odontologo no puede ser vacio o null");
        return odontologoRepository.buscarOdontoloPorIdEstado(id).orElseThrow(() ->  new OdontologoException("No existe Odontologo para el ID: "+id));
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.listar();
    }
    public void eliminarPorId(String id){
       Optional<Odontologo> odontologoOp = odontologoRepository.buscarOdontoloConTurnoActivo(id);
       if(odontologoOp.isPresent())
           throw new OdontologoException("No se puede eliminar el odontologo con el ID: "+id+" porque tiene un turno activo asociado.");
        Odontologo odontologo = odontologoOp.get();
        odontologo.estado = false;
        odontologoRepository.save(odontologo);
    }

    public boolean exiteOdotologo( String id){
      return   odontologoRepository.existsById(id);
    }
}
