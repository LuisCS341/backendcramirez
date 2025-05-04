package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Lindero")
@Data
public class Lindero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Lindero",nullable = false)
    private int idLindero;

    @Column(name = "ID_Lote",nullable = false)
    private int idLote;

    @Column(name="PorLaDerecha",nullable = false)
    private Float porLaDerecha;

    @Column(name="PorLaIzquierda",nullable = false)
    private Float porLaIzquierda;

    @Column(name="PorElFrente",nullable = false)
    private Float porElFrente;

    @Column(name="PorElFondo",nullable = false)
    private Float porElFondo;

}
