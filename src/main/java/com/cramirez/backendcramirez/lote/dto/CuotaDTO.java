package com.cramirez.backendcramirez.lote.dto;

import jakarta.persistence.Column;
import lombok.Data;

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
    private int cantidadCuotas;
    private String cantidadCuotaLetras;
    private String cantidadCuotaCuentaRecaudadora;
    private Float cantidadCuotaBanco;
}
