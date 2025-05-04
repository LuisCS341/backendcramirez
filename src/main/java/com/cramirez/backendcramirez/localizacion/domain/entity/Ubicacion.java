package com.cramirez.backendcramirez.localizacion.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ubicacion")
    private int idUbicacion;

    @Column(name = "Ubicacion")
    private String Ubicacion;
}
