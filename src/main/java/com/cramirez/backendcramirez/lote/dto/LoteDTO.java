package com.cramirez.backendcramirez.lote.dto;
import jakarta.persistence.Column;
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
    private String empresa;
    private String empresaVende;
    private Long rucVendedor;
    private String direccionVendedor;
    private String representanteLegalVendedor;
    private String dniVendedor;
    private Long numeroPartidaPoderVendedor;
    private String moneda;
    private String numCuenta;
    private String cci;
    private String fechaSale;
    private String fechaFirmaContratoDefinitivo;
    private String ubicacionLote;
    private String fechaInicioContrato;
    private String fechaCancelacionContrato;
    private Float precioMetroCuadrado;
    private String precioMetroCuadradoLetras;
    private String tipoRepresentante;
    private Double mantenimientoMensual;
    private String mantenimientoMensualLetras;
    private String estadoCuenta;
    private String montoDeudaLetra;
    private String fechaEntrega;
    private Float alicuota;
    private String alicuotaLetras;
    //-----------------------------------
    private String tipoProyecto;
    private String ubicacion;
    private String contrato;

    private LinderoDTO lindero;
    private CuotaDTO cuota;
    private MatrizDTO matriz;
    private List<CuotaExtraordinariaDTO> cuotasExtraordinarias;

}
