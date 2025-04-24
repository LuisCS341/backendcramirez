package com.cramirez.backendcramirez.entity.proyecto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TipoProyecto")
public class TipoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoProyecto")
    private int idTipoProyecto;
    @Column(name = "TipoProyecto", nullable = false)
    private String tipoProyecto;

    @Column(name = "EmpresaVende", nullable = false)
    private String empresaVende;

    @Column(name = "RUCVendedor", nullable = false)
    private float rucVendedor;

    @Column(name = "DireccionVendedor", nullable = false)
    private String direccionVendedor;

    @Column(name = "RepresentanteLegalVendedor", nullable = false)
    private String representanteLegalVendedor;

    @Column(name = "DNIVendedor", nullable = false)
    private Long dniVendedor;

    @Column(name = "NPartidaPoderVendedor", nullable = false)
    private Float nPartidaPoderVendedor;

    @Column(name = "Moneda", nullable = false)
    private String moneda;

    @Column(name = "NumCuenta", nullable = false)
    private String numCuenta;

    @Column(name = "CCI", nullable = false)
    private String cci;

    @Column(name = "FechaEntregaProyecto", nullable = false)
    private String fechaEntregaProyecto;

    @Column(name = "FechaFirmaContratoDefinitivo", nullable = false)
    private String fechaFirmaContratoDefinitivo;

    @Column(name = "AreaMatrizHas", nullable = false)
    private Double areaMatrizHas;

    @Column(name = "RegistroDE", nullable = false)
    private String registroDe;

    @Column(name = "PartidaMatriz", nullable = false)
    private String partidaMatriz;

    @Column(name = "UbicacionLote", nullable = false)
    private String ubicacionLote;

    @Column(name = "UnidadCatastralMatriz", nullable = false)
    private String unidadCatastralMatriz;

    @Column(name = "UrbanizacionMatriz", nullable = false)
    private String urbanizacionMatriz;

    @Column(name = "DistritoMatriz", nullable = false)
    private String distritoMatriz;

    @Column(name = "ProvinciaMatriz", nullable = false)
    private String provinciaMatriz;

    @Column(name = "DepartamentoMatriz", nullable = false)
    private String departamentoMatriz;

    @Column(name = "CompraVentaMatriz", nullable = false)
    private String compraVentaMatriz;

    @Column(name = "SituacionLegalMatriz", nullable = false)
    private String situacionLegalMatriz;

}
