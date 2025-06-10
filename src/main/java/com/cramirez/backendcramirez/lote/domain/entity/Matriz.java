package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Matriz")
@Data
public class Matriz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Matriz",nullable = false)
    private int idMatriz;
    @Column(name = "ID_Lote",nullable = false)
    private int idLote;

    @Column(name="ID_Distrito",nullable = false)
    private int idDistrito;

    @Column(name="ID_Provincia",nullable = false)
    private int idProvincia;

    @Column(name="ID_Departamento",nullable = false)
    private int idDepartamento;

    @Column(name="ID_Ubicacion",nullable = false)
    private int idUbicacion;

    @Column(name="AreaMatrizHas",nullable = false)
    private double areaMatrizHas;

    @Column(name="RegistroDE",nullable = false)
    private String registrosDE;

    @Column(name="PartidaMatriz",nullable = false)
    private String partidaMatriz;

    @Column(name="UnidadCatastral",nullable = false)
    private String unidadCatastral;

    @Column(name="UrbanizacionMatriz",nullable = false)
    private String urbanizacionMatriz;

    @Column(name="CompraVentaMatriz",nullable = false)
    private String compraventaMatriz;

    @Column(name="SituacionLegal",nullable = false)
    private String situacionLegal;

    @Column(name="Alicuota",nullable = false)
    private Float alicuota;

    @Column(name="AlicuotaLetras",nullable = false)
    private String alicuotaLetras;
}
