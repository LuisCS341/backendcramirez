package com.cramirez.backendcramirez.metadata.domain.entity;

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
