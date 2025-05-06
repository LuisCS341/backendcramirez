package com.cramirez.backendcramirez.cuotaExtraordinaria.domain.entity;

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

    @Column(name = "CuotaExtraordinaria")
    private Float cuotaExtraordinaria;

    @Column(name = "MantenimientoMensual")
    private Float mantenimientoMensual;

    @Column(name = "MantenimientoMensualLetras")
    private String mantenimientoMensualLetras;

    @Column(name = "EstadoCuenta")
    private String estadoCuenta;

    @Column(name = "MontoDeudaLetra")
    private String montoDeudaLetra;

    @Column(name = "CuotaPendientePago")
    private Float cuotaPendientePago;
}
