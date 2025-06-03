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

    @Column(name = "PagoInicial")
    private Float pagoInicial;

    @Column(name = "Separacion")
    private Float separacion;

    @Column(name = "CantidadCuotaExtraordinaria")
    private Float cantidadCuotaExtraordinaria;

    @Column(name = "MontoCuotaExtraordinaria")
    private Float montoCuotaExtraordinaria;

    @Column(name = "MontoDeudaLetra")
    private String montoDeudaLetra;

    @Column(name = "CuotaPendientePago")
    private Float cuotaPendientePago;

    @Column(name = "EstadoCuenta")
    private String estadoCuenta;

    @Column(name = "DiaPagoNumero")
    private Float diaPagoNumero;

    @Column(name = "DiaPagoLetras")
    private Float diaPagoLetras;

    @Column(name = "PonerMonto")
    private Float ponerMonto;
}
