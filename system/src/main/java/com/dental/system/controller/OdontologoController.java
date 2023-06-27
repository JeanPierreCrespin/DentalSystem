package com.dental.system.controller;

import com.dental.system.entities.Odontologo;
import com.dental.system.exception.OdontologoException;
import com.dental.system.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Odontologo odontologo) throws OdontologoException {
        return new ResponseEntity<>(odontologoService.guardar(odontologo), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<Odontologo> editar(@RequestBody Odontologo odontologo){
        return new ResponseEntity<>(odontologoService.modificar(odontologo), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable("id") String id) throws OdontologoException {
        return  new ResponseEntity<>(odontologoService.budcarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> buscarTodos(){
        return  new ResponseEntity<>(odontologoService.buscarTodos(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id){
        odontologoService.eliminarPorId(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
