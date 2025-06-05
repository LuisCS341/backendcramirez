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

    @Column(name="MontoLetras",nullable = false)
    private String montoLetras;

    @Column(name="PagoInicial",nullable = false)
    private Float pagoInicial;

    @Column(name="Separacion",nullable = false)
    private String separacion;

    @Column(name="MontoCuotas",nullable = false)
    private Float montoCuotas;

    @Column(name="CantidadCuotas",nullable = false)
    private int cantidadCuotas;

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

    @Column(name = "MantenimientoMensual", nullable = false)
    private Double mantenimientoMensual;

    @Column(name = "MantenimientoMensualLetras", nullable = false)
    private String mantenimientoMensualLetras;

    @Column(name = "FechaInicioContrato", nullable = false)
    private String fechaInicioContrato;

    @Column(name = "FechaCancelacionContrato", nullable = false)
    private String fechaCancelacionContrato;

}
