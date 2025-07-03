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

    private int cantidadCuotas;
    private String cantidadCuotaLetras;
    private String cantidadCuotaCuentaRecaudadora;
    private String cantidadCuotaBanco;
    private Float cuotaPendientePago;
}
