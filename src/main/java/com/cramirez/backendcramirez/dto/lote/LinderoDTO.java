package com.cramirez.backendcramirez.dto.lote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinderoDTO {


    private int idLindero;
    private int idLote;
    private Float porLaDerecha;
    private Float porLaIzquierda;
    private Float porElFrente;
    private Float porElFondo;
}
