package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cuota")
@Data
public class Cuota {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cuota",nullable = false)
    private int idCuota;

    @Column(name = "ID_Lote",nullable = false)
    private int idLote;

    @Column(name = "LetrasPendientePago")
    private String letrasPendientePago;

    @Column(name = "CuentaRecaudadora", nullable = false)
    private String cuentaRecaudadora;

    @Column(name = "CuotaInicialIncluyeSeparacion", nullable = false)
    private Float cuotaInicialIncluyeSeparacion;

    @Column(name = "CuotaInicialIncluyeSeparacionLetras", nullable = false)
    private String cuotaInicialIncluyeSeparacionLetras;

    @Column(name="MontoCuotas",nullable = false)
    private Float montoCuotas;

    @Column(name="MontoCuotaLetras",nullable = false)
    private String montoCuotaLetras;

    @Column(name = "FechaPago", nullable = false)
    private String fechaPago;

    @Column(name = "CuotaInicialBanco", nullable = false)
    private String cuotaInicialBanco;

    @Column(name="CantidadCuotas",nullable = false)
    private String cantidadCuotas;

    @Column(name="CantidadCuotaLetras",nullable = false)
    private String cantidadCuotaLetras;

    @Column(name="CantidadCuotaCuentaRecaudadora",nullable = false)
    private String cantidadCuotaCuentaRecaudadora;

    @Column(name = "CantidadCuotaBanco", nullable = false)
    private String cantidadCuotaBanco;

    @Column(name = "CuotaPendientePago", nullable = false)
    private String cuotaPendientePago;

}

