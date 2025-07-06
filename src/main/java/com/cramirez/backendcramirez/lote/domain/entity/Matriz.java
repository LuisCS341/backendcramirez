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
    private int idDistrito;

    @Column(name = "ID_Provincia", nullable = false)
    private int idProvincia;

    @Column(name = "ID_Departamento", nullable = false)
    private int idDepartamento;

    @Column(name = "Ubicacion", nullable = false)
    private String ubicacion;

    @Column(name = "Areamatrizhas", nullable = false)
    private String areaMatrizHas;

    @Column(name = "Registro", nullable = false)
    private String registro;

    @Column(name = "Partidamatriz", nullable = false)
    private String partidaMatriz;

    @Column(name = "Unidadcatastral", nullable = false)
    private String unidadCatastral;

    @Column(name = "Urbanizacion", nullable = false)
    private String urbanizacion;

    @Column(name = "Compraventa", nullable = false)
    private String compraventa;

    @Column(name = "Situacionlegal", nullable = false)
    private String situacionLegal;
}
