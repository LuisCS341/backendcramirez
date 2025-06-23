package com.cramirez.backendcramirez.lote.dto;
import lombok.Data;



import java.util.List;

@Data
public class LoteDTO {
    private int idLote;
    private int idOperario;
    private int idClienteLote;
    private String codigoLoteCliente;
    private int idClienteClone;
//-----------------------------------
    private int idTipoProyecto;
    private int idTipoContrato;
    private int idUbicacion;
    private String manzana;
    private int numeroLote;
    private Float areaLote;
    private String areaLoteLetras;
    private Float costoLote;
    private String costoLoteLetras;
    private Float montoCuotas;
    private String montoCuotaLetras;
    private int cantidadCuotas;
    private String cantidadCuotaLetras;
    private String cantidadCuotaCuentaRecaudadora;
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
    private String fechaInicioContrato;
    private String fechaCancelacionContrato;
    private Float saldoLote;
    private String saldoLoteLetras;
    private String cuentaRecaudadora;
    private String cuotaInicialBanco;
    private Float cantidadCuotaBanco;
    private String fechaPago;
    private Float cuotaInicialIncluyeSeparacion;
    private String cuotaInicialIncluyeSeparacionLetras;
    private Float precioMetroCuadrado;
    private String precioMetroCuadradoLetras;
    private String tipoRepresentante;

    //-----------------------------------
    private String tipoProyecto;
    private String ubicacion;
    private String contrato;

    private LinderoDTO lindero;
    private List<CuotaExtraordinariaDTO> cuotasExtraordinarias;
    private List<MatrizDTO> matriz;

}
