package com.cramirez.backendcramirez.lote.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatrizDTO {

    private int idMatriz;
    private int idLote;
    private int idDistritoMatriz;
    private int idProvinciaMatriz;
    private int idDepartamentoMatriz;
    private int  idUbicacion;
    private String areaMatrizHasMatriz;
    private String registroMatriz;
    private String partidaMatriz;
    private String unidadCatastralMatriz;
    private String urbanizacionMatriz;
    private String compraventaMatriz;
    private String situacionLegalMatriz;
    //-----------------------------
    private String txtdistritomatriz;
    private String txtprovinciamatriz;
    private String txtdepartamentomatriz;
    private String txtubicacionmatriz;
}
