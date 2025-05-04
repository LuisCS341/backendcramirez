package com.cramirez.backendcramirez.lote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "nombreProyecto",
        "ubicacion",
        "manzana",
        "numeroLote",
        "contrato",
        "areaLote",
        "costoLote",
        "montoLetras",
        "pagoInicial",
        "separacion",
        "montoCuotas",
        "catidadCuotas"
})
public class LoteDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idClienteLote;
//-----------------------------------
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoProyecto;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idUbicacion;
    private String manzana;
    private int numeroLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoContrato;
    private Float areaLote;
    private Float costoLote;
    private String montoLetras;
    private Float pagoInicial;
    private String separacion;
    private Float montoCuotas;
    private int catidadCuotas;
//-----------------------------------
    private String tipoProyecto;
    private String ubicacion;
    private String contrato;
}
