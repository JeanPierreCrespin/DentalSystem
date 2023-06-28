package com.dental.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "PACIENTES")
public class Paciente{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;
    public String nombre;
    public String apellido;
    public String dni;
    public Date fechaAlta;
    public Boolean estado;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    public Set<Direccion> direccion;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    public Set<Turno> turnos;

}
