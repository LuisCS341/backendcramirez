package com.cramirez.backendcramirez.cliente.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ClienteConyuge")
public class ClienteConyuge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ClienteConyuge")
    private int idClienteConyuge;

    @Column(name = "ID_Cliente",nullable = false)
    private int idCliente;

    @OneToOne
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "ID_Cliente", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name = "ID_Nacionalidad",nullable = false)
    private int idNacionalidadConyuge;

    @Column(name = "ID_Prefijo",nullable = false)
    private int idPrefijoConyuge;

    @Column(name = "ID_Identificacion",nullable = false)
    private int idIdentificacionConyuge;

    @Column(name = "ID_Residencia",nullable = false)
    private int idResidenciaConyuge;

    @Column(name = "ID_Operario",nullable = false)
    private int idOperarioConyuge;

    @Column(name = "ID_Departamento",nullable = false)
    private int idDepartamentoConyuge;

    @Column(name = "ID_Provincia",nullable = false)
    private int idProvinciaConyuge;

    @Column(name = "ID_Distrito",nullable = false)
    private int idDistritoConyuge;

    @Column(name = "NombresApellidosConyugeCliente",nullable = false)
    private String NombresApellidosConyuge;

    @Column(name = "DireccionConyugeCliente",nullable = false)
    private String DireccionConyuge;

    @Column(name = "CorreoElectronicoConyugeCliente",nullable = false)
    private String CorreoElectronicoConyuge;

    @Column(name = "CelularConyugeCliente",nullable = false)
    private String CelularConyuge;

    @Column(name = "OcupacionConyugeCliente",nullable = false)
    private String ocupacionConyuge;

    @Column(name = "NumeroIdentificacionConyugeCliente",nullable = false)
    private String NumeroIdentificacionConyuge;



}
