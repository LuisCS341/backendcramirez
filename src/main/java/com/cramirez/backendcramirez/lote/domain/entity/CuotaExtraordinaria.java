package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CuotaExtraordinaria")
@Getter
@Setter
public class CuotaExtraordinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CuotaExtraordinaria")
    private int idCuotaExtraordinaria;

    @Column(name = "ID_Lote")
    private int idLote;

    @Column(name = "CantidadCuotaExtraordinaria")
    private Float cantidadCuotaExtraordinaria;

    @Column(name = "MontoCuotaExtraordinaria")
    private Float montoCuotaExtraordinaria;

    @Column(name = "MantenimientoMensual")
    private Double mantenimientoMensual;

    @Column(name = "MantenimientoMensualLetras")
    private String mantenimientoMensualLetras;

    @Column(name = "EstadoCuenta")
    private String estadoCuenta;

    @Column(name = "MontoDeudaLetra")
    private String montoDeudaLetra;

    @Column(name = "CuotaPendientePago")
    private Float cuotaPendientePago;

    @Column(name = "LetrasPendientePago")
    private String letrasPendientePago;

    @Column(name = "FechaEntrega")
    private String fechaEntrega;

    @Column(name = "CartaNoAdeudo")
    private String cartaNoAdeudo;

    @Column(name = "CertificadoLote")
    private String certificadoLote;

    @Column(name = "MediosPago")
    private String mediosPago;

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
