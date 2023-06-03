package com.dental.system.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@Entity
public class Turno {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private  String id;
    private Date fecha;

    @OneToOne
    private Paciente paciente;
    @OneToOne
    private Odontologo odontologo;

}
