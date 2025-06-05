package com.cramirez.backendcramirez.cuotaExtraordinaria.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Float pagoInicial;
    private Float separacion;
    private Float cantidadCuotaExtraordinaria;
    private Float montoCuotaExtraordinaria;
    private String montoDeudaLetra;
    private Float cuotaPendientePago;
    private String estadoCuenta;
    private Float diaPagoNumero;
    private Float diaPagoLetras;
    private Float ponerMonto;
}
