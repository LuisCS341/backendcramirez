package com.cramirez.backendcramirez.lote.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CuotaDTO {

    @Column(name = "ID_Cuota",nullable = false)
    private int idCuota;
    private int idLote;
    private String letrasPendientePago;
    private String cuentaRecaudadora;
    private Float cuotaInicialIncluyeSeparacion;
    private String cuotaInicialIncluyeSeparacionLetras;
    private Float montoCuotas;
    private String montoCuotaLetras;
    private String fechaPago;
    private String cuotaInicialBanco;
    private Float saldoLote;
    private String saldoLoteLetras;
    private String cantidadCuotas;
    private String cantidadCuotaLetras;
    private String cantidadCuotaCuentaRecaudadora;
    private String cantidadCuotaBanco;
    private String cuotaPendientePago;
    private String mediosPago;
    private String estadoCuenta;
    private String montoDeudaLetra;
}
