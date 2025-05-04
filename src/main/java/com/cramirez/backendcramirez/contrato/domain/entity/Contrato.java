package com.cramirez.backendcramirez.contrato.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "Contrato")
@Getter
@Setter
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Contrato")
    private int idContrato;

    @Column(name = "ID_Cliente", nullable = false)
    private int idCliente;

    @Column(name = "ID_TipoContrato", nullable = false)
    private int idTipoContrato;

    @Column(name = "ID_Lote", nullable = false)
    private int idLote;

    @Column(name = "Numero_Contrato", nullable = false)
    private String numeroContrato;

    @Column(name = "Fecha_Inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "Fecha_Cancelacion", nullable = false)
    private Date fechaCanelacion;

    @Column(name = "Mantenimiento_Mensual", nullable = false)
    private Float mantenimientoMensual;

    @Column(name = "Mantenimiento_Letras", nullable = false)
    private String mantenimientoLetras;
}
