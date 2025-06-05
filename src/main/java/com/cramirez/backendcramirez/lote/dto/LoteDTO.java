package com.cramirez.backendcramirez.lote.dto;

import com.cramirez.backendcramirez.cuotaExtraordinaria.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
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
        "ubicacionLote",
        "manzana",
        "numeroLote",
        "contrato",
        "areaLote",
        "costoLote",
        "montoLetras",
        "pagoInicial",
        "separacion",
        "montoCuotas",
        "cantidadCuotas"
})
public class LoteDTO {
    private int idLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idClienteLote;
//-----------------------------------
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoProyecto;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoContrato;
    private int idUbicacion;
    private String manzana;
    private int numeroLote;
    private Float areaLote;
    private String areaLoteLetras;
    private Float costoLote;
    private String montoLetras;
    private Float pagoInicial;
    private String separacion;
    private Float montoCuotas;
    private int cantidadCuotas;
    private String empresa;
    private String empresaVende;
    private Long rucVendedor;
    private String direccionVendedor;
    private String representanteLegalVendedor;
    private Long dniVendedor;
    private Long numeroPartidaPoderVendedor;
    private String moneda;
    private String numCuenta;
    private String cci;
    private String fechaSale;
    private String fechaFirmaContratoDefinitivo;
    private String ubicacionLote;
    private Double mantenimientoMensual;
    private String mantenimientoMensualLetras;
    private String fechaInicioContrato;
    private String fechaCancelacionContrato;

    //-----------------------------------
    private String tipoProyecto;
    private String ubicacion;
    private String contrato;

    private LinderoDTO lindero;
    private List<CuotaExtraordinariaDTO> cuotasExtraordinarias;
    private List<MatrizDTO> matriz;
    public LoteDTO() {}

}
