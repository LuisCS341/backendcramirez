package com.cramirez.backendcramirez.lote.dto;

import com.cramirez.backendcramirez.cuotaExtraordinaria.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Data
@JsonPropertyOrder({
        "nombreProyecto",
        "ubicacion",
         "empresa",
        "empresaVende",
        "rucVendedor",
        "direccionVendedor",
        "representanteLegalVendedor",
        "dniVendedor",
        "nPartidaPoderVendedor",
        "moneda",
        "numCuenta",
        "cci",
        "fechaSale",
        "fechaFirmaContratoDefinitivo",
        "areaMatrizHas",
        "registroDe",
        "partidaMatriz",
        "ubicacionLote",
        "unidadCatastralMatriz",
        "urbanizacionMatriz",
        "distritoMatriz",
        "provinciaMatriz",
        "departamentoMatriz",
        "compraVentaMatriz",
        "situacionLegalMatriz",
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int idLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idClienteLote;
//-----------------------------------
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoProyecto;
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
    private String empresa;
    private String empresaVende;
    private float rucVendedor;
    private String direccionVendedor;
    private String representanteLegalVendedor;
    private Long dniVendedor;
    private Long numeroPartidaPoderVendedor;
    private String moneda;
    private String numCuenta;
    private String cci;
    private String fechaSale;
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
//-----------------------------------
    private String tipoProyecto;
    private String ubicacion;
    private String contrato;

    private LinderoDTO lindero;
    private List<CuotaExtraordinariaDTO> cuotasExtraordinarias;
    private List<MatrizDTO> matriz;

}
