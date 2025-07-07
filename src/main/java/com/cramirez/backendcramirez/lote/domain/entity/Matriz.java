package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Matriz")
@Data
public class Matriz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Matriz", nullable = false)
    private int idMatriz;

    @Column(name = "ID_Lote", nullable = false)
    private int idLote;

    @Column(name = "ID_Distrito", nullable = false)
    private int idDistritoMatriz;

    @Column(name = "ID_Provincia", nullable = false)
    private int idProvinciaMatriz;

    @Column(name = "ID_Departamento", nullable = false)
    private int idDepartamentoMatriz;

    @Column(name = "ID_Ubicacion", nullable = false)
    private int ubicacionMatriz;

    @Column(name = "Areamatrizhas", nullable = false)
    private String areaMatrizHasMatriz;

    @Column(name = "Registro", nullable = false)
    private String registroMatriz;

    @Column(name = "Unidadcatastral", nullable = false)
    private String unidadCatastralMatriz;

    @Column(name = "Urbanizacion", nullable = false)
    private String urbanizacionMatriz;

    @Column(name = "Compraventa", nullable = false)
    private String compraventaMatriz;

    @Column(name = "Situacionlegal", nullable = false)
    private String situacionLegalMatriz;

    @Column(name = "Partidamatriz", nullable = false)
    private String partidaMatriz;
}