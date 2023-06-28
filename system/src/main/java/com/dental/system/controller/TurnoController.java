package com.dental.system.controller;

import com.dental.system.entities.Turno;
import com.dental.system.exception.OdontologoException;
import com.dental.system.exception.PacienteException;
import com.dental.system.exception.TurnoException;
import com.dental.system.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turno")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping("/crear")
    public ResponseEntity<Turno> crear(@RequestBody Turno turno) throws PacienteException, OdontologoException, TurnoException {
        return new ResponseEntity<>(turnoService.guardar(turno), HttpStatus.CREATED);
    }

    @PutMapping("/editar")
    public ResponseEntity<Turno> editar(@RequestBody Turno turno) throws PacienteException, OdontologoException {
        return new ResponseEntity<>(turnoService.modificar(turno), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable("id") String id){
        return  new ResponseEntity<>(turnoService.buscarPorId(id), HttpStatus.OK);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> buscarTodos(){
        return  new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") String id){
        turnoService.eliminar(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
