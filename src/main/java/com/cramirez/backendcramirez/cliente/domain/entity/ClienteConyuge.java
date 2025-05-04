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
    private Integer idClienteConyuge;

    @Column(name = "ID_Cliente",nullable = false)
    private Integer idCliente;

    @Column(name = "ID_Nacionalidad",nullable = false)
    private Integer idNacionalidadConyuge;

    @Column(name = "ID_Prefijo",nullable = false)
    private Integer idPrefijoConyuge;

    @Column(name = "ID_Identificacion",nullable = false)
    private Integer idIdentificacionConyuge;

    @Column(name = "ID_Residencia",nullable = false)
    private Integer idResidenciaConyuge;

    @Column(name = "ID_Operario",nullable = false)
    private Integer idOperarioConyuge;

    @Column(name = "ID_Departamento",nullable = false)
    private Integer idDepartamentoConyuge;

    @Column(name = "ID_Provincia",nullable = false)
    private Integer idProvinciaConyuge;

    @Column(name = "ID_Distrito",nullable = false)
    private Integer idDistritoConyuge;

    @Column(name = "Nombres_Apellidos",nullable = false)
    private String NombresApellidosConyuge;

    @Column(name = "Direccion",nullable = false)
    private String DireccionConyuge;

    @Column(name = "Correo_Electronico",nullable = false)
    private String CorreoElectronicoConyuge;

    @Column(name = "Celular",nullable = false)
    private String CelularConyuge;

    @Column(name = "Ocupacion",nullable = false)
    private String ocupacionConyuge;

    @Column(name = "NumeroIdentificacion",nullable = false)
    private String NumeroIdentificacionConyuge;



}
