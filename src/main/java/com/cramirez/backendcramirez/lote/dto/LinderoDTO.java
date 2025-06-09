package com.cramirez.backendcramirez.lote.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinderoDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idLindero;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idLote;
    private Float porLaDerecha;
    private Float porLaIzquierda;
    private Float porElFrente;
    private Float porElFondo;
}
