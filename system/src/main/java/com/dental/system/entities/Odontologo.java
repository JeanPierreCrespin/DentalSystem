package com.dental.system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(toBuilder = true)
@Table(name = "ODONTOLOGOS")
public class Odontologo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public String id;
    public String matricula;
    public String nombre;
    public String apellido;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "odontologo_id")
    public Set<Direccion> direccion;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    public Set<Turno> turnos;

}
