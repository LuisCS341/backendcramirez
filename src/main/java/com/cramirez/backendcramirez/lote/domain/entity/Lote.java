package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Lote")
@Data
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Lote",nullable = false)
    private int idLote;
    @Column(name = "ID_Cliente",nullable = false)
    private int idClienteLote;

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

    @Column(name="CatidadCuotas",nullable = false)
    private int catidadCuotas;

}
