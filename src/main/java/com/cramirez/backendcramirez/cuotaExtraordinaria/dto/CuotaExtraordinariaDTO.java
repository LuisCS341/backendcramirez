package com.cramirez.backendcramirez.cuotaExtraordinaria.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuotaExtraordinariaDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idCuotaExtraordinaria;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idLote;
    private Float cantidadCuotaExtraordinaria;
    private Float montoCuotaExtraordinaria;
    private Float mantenimientoMensual;
    private String mantenimientoMensualLetras;
    private String estadoCuenta;
    private String montoDeudaLetra;
    private Float cuotaPendientePago;
}
