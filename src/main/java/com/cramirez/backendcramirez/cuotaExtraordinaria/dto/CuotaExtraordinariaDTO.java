package com.cramirez.backendcramirez.cuotaExtraordinaria.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuotaExtraordinariaDTO {

    private int idCuotaExtraordinaria;
    private int idLote;
        private Float cuotaExtraordinaria;
    private Float mantenimientoMensual;
    private String mantenimientoMensualLetras;
    private String estadoCuenta;
    private String montoDeudaLetra;
    private Float cuotaPendientePago;
}
