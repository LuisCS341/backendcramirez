package com.cramirez.backendcramirez.localizacion.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "Residencia")
@Getter
@Setter
@Entity
public class Residencia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Residencia")
    private int idResidencia;

    @Column(name = "Residencia")
    private String Residencia;
}
