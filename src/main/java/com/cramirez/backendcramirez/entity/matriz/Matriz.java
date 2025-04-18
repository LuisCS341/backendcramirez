package com.cramirez.backendcramirez.entity.matriz;

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

    @Column(name="Area_Matriz_Has",nullable = false)
    private Float areaMatrizHas;

    @Column(name="Registros_DE",nullable = false)
    private String registrosDE;

    @Column(name="Partida_Matriz",nullable = false)
    private String partidaMatriz;

    @Column(name="Unidad_Catastral",nullable = false)
    private String unidadCatastral;

    @Column(name="Urbanizacion_Matriz",nullable = false)
    private String urbanizacionMatriz;

    @Column(name="Compraventa_Matriz",nullable = false)
    private String compraventaMatriz;

    @Column(name="Situacion_Legal",nullable = false)
    private String situacionLegal;

    @Column(name="Alicuota",nullable = false)
    private Float alicuota;

    @Column(name="Alicuota_Letras",nullable = false)
    private String alicuotaLetras;
}
