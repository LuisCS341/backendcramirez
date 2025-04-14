package com.cramirez.backendcramirez.entity.lote;

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

    @Column(name="NumeroLote",nullable = false)
    private int numeroLote;

    @Column(name="AreaLote",nullable = false)
    private Float areaLote;

    @Column(name="Manzana",nullable = false)
    private String manzana;


}
