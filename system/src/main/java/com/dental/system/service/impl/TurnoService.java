package com.dental.system.service.impl;

import com.dental.system.entities.Odontologo;
import com.dental.system.entities.Paciente;
import com.dental.system.entities.Turno;
import com.dental.system.exception.OdontologoException;
import com.dental.system.exception.PacienteException;
import com.dental.system.exception.TurnoException;
import com.dental.system.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        validarOdontologoPacienteFechaHora(turno);

        Odontologo odontologo = odontologoService.buscarPorId(turno.odontologo.id);
        Paciente paciente = pacienteService.buscarPorId(turno.paciente.id);


        Optional<Turno> turnoOdontologoPacienteOptional = turnoRepository.findByFechaYHoraAndOdontologoAndPaciente(turno.fechaYHora, odontologo,paciente);
        if(turnoOdontologoPacienteOptional.isPresent())
            throw new TurnoException("Ya hay un turn o cargado para la fecha: "+turno.fechaYHora+" y Odontologo: "+ odontologo.nombre+ "con Matricula: "+odontologo.matricula+" y Paciente: "+paciente.nombre+ " "+paciente.apellido);
        Optional<Turno> turnoOptional = turnoRepository.findByFechaYHoraAndAndOdontologo(turno.fechaYHora, odontologo);

        if(turnoOptional.isPresent())
            throw new TurnoException("El odontologo ya tiene un turno asignado a la misma hora y fecha ");
        turno.odontologo = odontologo;
        turno.paciente = paciente;
        turno.estado = true;
        return turnoRepository.save(turno);
    }

    private  void validarOdontologoPacienteFechaHora(Turno turno) {
        if(turno.fechaYHora.isBefore(LocalDateTime.now()))
            throw new TurnoException("La fecha y hora incorecta");

        if(turno.odontologo == null || turno.odontologo.id.isEmpty() || !odontologoService.exiteOdotologo(turno.odontologo.id) )
            throw new TurnoException("Se debe selecionar un odontologo");


        if(turno.paciente == null || turno.paciente.id.isEmpty() || !pacienteService.existePaciente(turno.paciente.id))
            throw new TurnoException("Se debe selecionar un paciente");
    }

    public Turno modificar(Turno turno) throws OdontologoException, PacienteException {
        if(!existeTurno(turno.id)){
            throw new TurnoException("El turno con el Id: "+turno.id+" no existe");
        }
        validarOdontologoPacienteFechaHora(turno);

        Odontologo odontologo = odontologoService.buscarPorId(turno.odontologo.id);
        Paciente paciente =paciente = pacienteService.buscarPorId(turno.paciente.id);


        Turno turnoR = buscarPorId(turno.id);
        turnoR.fechaYHora = turno.fechaYHora == null? turnoR.fechaYHora : turno.fechaYHora;
        turnoR.odontologo = odontologo;
        turnoR.paciente = paciente;
        return turnoRepository.save(turnoR);
    }
;    public Turno buscarPorId(String id){
        return turnoRepository.buscarTurnoPorId(id).orElseThrow( () -> new TurnoException("No se encontro turno para el ID: "+id));
    }

    public List<Turno> listarTurnos(){
        return turnoRepository.listar();
    }

    public void eliminar(String id){
         Turno turno = buscarPorId(id);
          turno.estado = false;
          turnoRepository.save(turno);
    }

    public Boolean existeTurno(String id){
    return turnoRepository.existsById(id);
    }
}
