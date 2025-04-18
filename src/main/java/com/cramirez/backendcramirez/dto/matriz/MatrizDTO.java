package com.cramirez.backendcramirez.dto.matriz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatrizDTO {

    private int idMatriz;
    private int idLote;
    private int idDistrito;
    private int idProvincia;
    private int idDepartamento;
    private int idUbicacion;
    private Float areaMatrizHas;
    private String registrosDE;
    private String partidaMatriz;
    private String unidadCatastral;
    private String urbanizacionMatriz;
    private String compraventaMatriz;
    private String situacionLegal;
    private Float alicuota;
    private String alicuotaLetras;
}
