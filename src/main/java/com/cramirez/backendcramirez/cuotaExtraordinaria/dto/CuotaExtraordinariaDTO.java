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
    private Float cantidadCuotaExtraordinaria;
    private Float montoCuotaExtraordinaria;
    private Double mantenimientoMensual;
    private String mantenimientoMensualLetras;
    private String estadoCuenta;
    private String montoDeudaLetra;
    private Float cuotaPendientePago;
    private String letrasPendientePago;
    private String fechaEntrega;
    private String cartaNoAdeudo;
    private String certificadoLote;
    private String mediosPago;
    private String plano1;
    private String plano2;
    private String envioMinuta;
    private String fechaCita;
    private String horaCita;
    private String modificarMinuta;
    private String minutaEscaneada;
    private String expNotaria;

}
