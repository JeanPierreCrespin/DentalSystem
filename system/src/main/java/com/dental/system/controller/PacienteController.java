package com.dental.system.controller;

import com.dental.system.entities.Odontologo;
import com.dental.system.entities.Paciente;
import com.dental.system.exception.PacienteException;
import com.dental.system.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody Paciente paciente){
        return new ResponseEntity<>(pacienteService.guardar(paciente), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<Paciente> editar(@RequestBody Paciente paciente){
        return new ResponseEntity<>(pacienteService.modificar(paciente), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable("id") String id) throws PacienteException {
        return  new ResponseEntity<>(pacienteService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> buscarTodos(){
        return new ResponseEntity<>(pacienteService.buscarTodos(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id){
        pacienteService.eliminar(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
