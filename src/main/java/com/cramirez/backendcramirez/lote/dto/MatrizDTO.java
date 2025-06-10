package com.cramirez.backendcramirez.lote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatrizDTO {


    private int idMatriz;
    private int idLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDistrito;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idProvincia;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDepartamento;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idUbicacion;
    private Double areaMatrizHas;
    private String registrosDE;
    private String partidaMatriz;
    private String unidadCatastral;
    private String urbanizacionMatriz;
    private String compraventaMatriz;
    private String situacionLegal;
    private Float alicuota;
    private String alicuotaLetras;
//--------------------------------------------------
    private String departamento;
    private String provincia;
    private String distrito;
    private String ubicacion;



}
