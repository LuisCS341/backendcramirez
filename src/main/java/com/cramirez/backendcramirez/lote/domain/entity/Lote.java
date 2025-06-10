package com.cramirez.backendcramirez.lote.domain.entity;

import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "Lote")
@Data
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Lote",nullable = false)
    private int idLote;

    @Column(name = "ID_Cliente", nullable = false   , updatable = false)
    private int idClienteLote;

    @Column(name = "CodigoLoteCliente", nullable = false )
    private String codigoLoteCliente;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "ID_Cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name="ID_TipoProyecto",nullable = false)
    private int idTipoProyecto;

    @Column(name="ID_Ubicacion",nullable = false)
    private int idUbicacion;
    @Column(name="ID_Operario",nullable = false)
    private int idOperario;
    @Column(name="ID_TipoContrato",nullable = false)
    private int idTipoContrato;

    @Column(name="Manzana",nullable = false)
    private String manzana;

    @Column(name="NumeroLote",nullable = false)
    private int numeroLote;

    @Column(name="AreaLote",nullable = false)
    private Float areaLote;

    @Column(name="AreaLoteLetras",nullable = false)
    private String areaLoteLetras;

    @Column(name="CostoLote",nullable = false)
    private Float costoLote;

     @Column(name="CostoLoteLetras",nullable = false)
     private String costoLoteLetras;


    @Column(name="MontoCuotas",nullable = false)
    private Float montoCuotas;

    @Column(name="MontoCuotaLetras",nullable = false)
    private String montoCuotaLetras;

    @Column(name="CantidadCuotas",nullable = false)
    private int cantidadCuotas;

    @Column(name="CantidadCuotaLetras",nullable = false)
    private String cantidadCuotaLetras;

    @Column(name="CantidadCuotaCuentaRecaudadora",nullable = false)
    private String cantidadCuotaCuentaRecaudadora;

    @Column(name = "Empresa", nullable = false)
    private String empresa;

    @Column(name = "EmpresaVende", nullable = false)
    private String empresaVende;

    @Column(name = "RUCVendedor", nullable = false)
    private Long rucVendedor;

    @Column(name = "DireccionVendedor", nullable = false)
    private String direccionVendedor;

    @Column(name = "RepresentanteLegalVendedor", nullable = false)
    private String representanteLegalVendedor;

    @Column(name = "DNIVendedor", nullable = false)
    private Long dniVendedor;

    @Column(name = "NPartidaPoderVendedor",  nullable = true)
    private Long numeroPartidaPoderVendedor;

    @Column(name = "Moneda", nullable = false)
    private String moneda;

    @Column(name = "NumCuenta", nullable = false)
    private String numCuenta;

    @Column(name = "CCI", nullable =     false)
    private String cci;

    @Column(name = "FechaSale", nullable = false)
    private String fechaSale;

    @Column(name = "FechaFirmaContratoDefinitivo", nullable = false)
    private String fechaFirmaContratoDefinitivo;

    @Column(name = "UbicacionLote", nullable = false)
    private String ubicacionLote;

    @Column(name = "FechaInicioContrato", nullable = false)
    private String fechaInicioContrato;

    @Column(name = "FechaCancelacionContrato", nullable = false)
    private String fechaCancelacionContrato;

    @Column(name = "SaldoLote", nullable = false)
    private Float saldoLote;

    @Column(name = "SaldoLoteLetras", nullable = false)
    private String saldoLoteLetras;

    @Column(name = "CuentaRecaudadora", nullable = false)
    private String cuentaRecaudadora;

    @Column(name = "CuotaInicialBanco", nullable = false)
    private String cuotaInicialBanco;

    @Column(name = "CantidadCuotaBanco", nullable = false)
    private Float cantidadCuotaBanco;

    @Column(name = "FechaPago", nullable = false)
    private String fechaPago;

    @Column(name = "CuotaInicialIncluyeSeparacion", nullable = false)
    private Float cuotaInicialIncluyeSeparacion;

    @Column(name = "CuotaInicialIncluyeSeparacionLetras", nullable = false)
    private String cuotaInicialIncluyeSeparacionLetras;

    @Column(name = "PrecioMetroCuadrado", nullable = false)
    private Float precioMetroCuadrado;

    @Column(name = "PrecioMetroCuadradoLetras", nullable = false)
    private String precioMetroCuadradoLetras;

    @Column(name = "TipoRepresentante", nullable = false)
    private String tipoRepresentante;

}
