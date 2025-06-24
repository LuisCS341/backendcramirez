package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Documento")
@Data
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Documento",nullable = false)
    private int idDocumento;

    @Column(name = "ID_Lote",nullable = false)
    private int idLote;

    @Column(name = "CartaNoAdeudo")
    private String cartaNoAdeudo;

    @Column(name = "CertificadoLote")
    private String certificadoLote;

    @Column(name = "Plano1")
    private String plano1;

    @Column(name = "Plano2")
    private String plano2;

    @Column(name = "EnvioMinuta")
    private String envioMinuta;

    @Column(name = "FechaCita")
    private String fechaCita;

    @Column(name = "HoraCita")
    private String horaCita;

    @Column(name = "ModificarMinuta")
    private String modificarMinuta;

    @Column(name = "MinutaEscaneada")
    private String minutaEscaneada;

    @Column(name = "ExpNotaria")
    private String expNotaria;
}
