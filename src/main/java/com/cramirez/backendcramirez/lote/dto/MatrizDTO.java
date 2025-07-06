package com.cramirez.backendcramirez.lote.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatrizDTO {

    private int idMatriz;
    private int idLote;
    private int idDistrito;
    private int idProvincia;
    private int idDepartamento;
    private String ubicacion;
    private String areaMatrizHas;
    private String registro;
    private String partidaMatriz;
    private String unidadCatastral;
    private String urbanizacion;
    private String compraventa;
    private String situacionLegal;
}
