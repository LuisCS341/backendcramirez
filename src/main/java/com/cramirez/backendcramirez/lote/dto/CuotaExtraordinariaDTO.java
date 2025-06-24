package com.cramirez.backendcramirez.lote.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuotaExtraordinariaDTO {

    private int idCuotaExtraordinaria;
    private int idLote;
    private Float cantidadCuotaExtraordinaria;
    private Float montoCuotaExtraordinaria;
    private String mediosPago;
}
