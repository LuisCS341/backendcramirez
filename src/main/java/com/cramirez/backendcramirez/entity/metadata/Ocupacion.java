package com.cramirez.backendcramirez.entity.metadata;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Ocupacion")
@Getter
@Setter
public class Ocupacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ocupacion")
    private int idOcupacion;

    @Column(name = "Ocupacion")
    private String nombreOcupacion;

}
