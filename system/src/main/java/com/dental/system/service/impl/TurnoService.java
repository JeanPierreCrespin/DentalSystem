package com.dental.system.service.impl;

import com.dental.system.entities.Odontologo;
import com.dental.system.entities.Paciente;
import com.dental.system.entities.Turno;
import com.dental.system.exception.OdontologoException;
import com.dental.system.exception.PacienteException;
import com.dental.system.exception.TurnoException;
import com.dental.system.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private PacienteService pacienteService;

    public Turno guardar(Turno turno) throws OdontologoException, PacienteException {

        validarOdontologoPaciente(turno);

        Odontologo odontologo = odontologoService.budcarPorId(turno.odontologo.id);
        Paciente paciente = pacienteService.buscarPorId(turno.paciente.id);


        Optional<Turno> turnoOdontologoPacienteOptional = turnoRepository.findByFechaYHoraAndOdontologoAndPaciente(turno.fechaYHora, odontologo,paciente);
        if(turnoOdontologoPacienteOptional.isPresent())
            throw new TurnoException("Ya hay un turn o cargado para la fecha: "+turno.fechaYHora+" y Odontologo: "+ odontologo.nombre+ "con Matricula: "+odontologo.matricula+" y Paciente: "+paciente.nombre+ " "+paciente.apellido);
        Optional<Turno> turnoOptional = turnoRepository.findByFechaYHoraAndAndOdontologo(turno.fechaYHora, odontologo);

        if(turnoOptional.isPresent())
            throw new TurnoException("El odontologo ya tiene un turno asignado a la misma hora y fecha ");
        turno.fechaYHora = turno.fechaYHora;
        turno.odontologo = odontologo;
        turno.paciente = paciente;
        return turnoRepository.save(turno);
    }

    private  void validarOdontologoPaciente(Turno turno) {
        if(turno.paciente.id.isEmpty() || turno.paciente.id.length() <= 0 || !odontologoService.exiteOdotologo(turno.odontologo.id) )
            throw new TurnoException("Se debe selecionar un paciente");


        if(turno.odontologo.id.isEmpty() || turno.odontologo.id.length() <= 0 || !pacienteService.existePaciente(turno.paciente.id))
            throw new TurnoException("Se debe selecionar un odontologo");
    }

    public Turno modificar(Turno turno) throws OdontologoException, PacienteException {
        if(!existeTurno(turno.id)){
            throw new TurnoException("El turno con el Id: "+turno.id+" no existe");
        }
        validarOdontologoPaciente(turno);

        Odontologo odontologo = odontologoService.budcarPorId(turno.odontologo.id);
        Paciente paciente =paciente = pacienteService.buscarPorId(turno.paciente.id);

        Turno turnoR = buscarPorId(turno.id);
        turnoR.fechaYHora = turno.fechaYHora == null? turnoR.fechaYHora : turno.fechaYHora;
        turnoR.odontologo = odontologo;
        turnoR.paciente = paciente;
        return turnoRepository.save(turnoR);
    }
;    public Turno buscarPorId(String id){
        return turnoRepository.findById(id).orElseThrow( () -> new TurnoException("No se encontro turno para el ID: "+id));
    }

    public List<Turno> listarTurnos(){
        return turnoRepository.findAll();
    }

    public void eliminar(String id){
        turnoRepository.deleteById(id);
    }

    public Boolean existeTurno(String id){
    return turnoRepository.existsById(id);
    }
}
