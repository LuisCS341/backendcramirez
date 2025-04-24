package com.cramirez.backendcramirez.dto.proyecto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoProyectoDTO {
    private int idTipoProyecto;
    private String tipoProyecto;
    private String empresaVende;
    private float rucVendedor;
    private String direccionVendedor;

    private String representanteLegalVendedor;
    private Long dniVendedor;
    private Float nPartidaPoderVendedor;
    private String moneda;
    private String numCuenta;
    private String cci;
    private String fechaEntregaProyecto;
    private String fechaFirmaContratoDefinitivo;
    private Double areaMatrizHas;
    private String registroDe;
    private String partidaMatriz;
    private String ubicacionLote;
    private String unidadCatastralMatriz;
    private String urbanizacionMatriz;
    private String distritoMatriz;
    private String provinciaMatriz;
    private String departamentoMatriz;
    private String compraVentaMatriz;
    private String situacionLegalMatriz;

}
