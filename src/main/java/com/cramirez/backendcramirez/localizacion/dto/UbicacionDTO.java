package com.cramirez.backendcramirez.localizacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionDTO {

    private int idUbicacion;
    private String Ubicacion;
    private String proyecto;
    private String empresaVende;
    private float rucVendedor;
    private String direccionVendedor;
    private String representanteLegalVendedor;
    private Long dniVendedor;
    private Float nPartidaPoderVendedor;
    private String moneda;
    private String numCuenta;
    private String cci;
    private String fechaSale;
    private String fechaFirmaContratoDefinitivo;
    private Double areaMatrizHas;
    private String registroDe;
    private String partidaMatriz;
    private String unidadCatastralMatriz;
    private String urbanizacionMatriz;
    private String distritoMatriz;
    private String provinciaMatriz;
    private String departamentoMatriz;
    private String compraVentaMatriz;
    private String situacionLegalMatriz;
}
