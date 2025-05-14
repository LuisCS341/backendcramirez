package com.cramirez.backendcramirez.cliente.domain.entity;

import com.cramirez.backendcramirez.lote.domain.entity.Lote;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente",nullable = false)
    private int idCliente;

    @Column(name = "ID_Identificacion",nullable = false)
    private int idIdentificacion;

    @Column(name = "ID_EstadoCivil",nullable = false)
    private int idEstadoCivil;

    @Column(name = "ID_Nacionalidad",nullable = false)
    private int idNacionalidad;

    @Column(name = "ID_Residencia",nullable = false)
    private int idResidencia;

    @Column(name = "ID_Operario",nullable = false)
    private int idOperario;

    @Column(name = "Nombres_Apellidos",nullable = false)
    private String nombresApellidos;

    @Column(name = "Ocupacion",nullable = false)
    private String ocupacion;

    @Column(name = "Direccion",nullable = false)
    private String direccion;

    @Column(name = "Correo_Electronico",nullable = false)
    private String correoElectronico;

    @Column(name = "Celular",nullable = false)
    private int celularCliente;

    @Column(name = "NumeroIdentificacion",nullable = false,unique = true)
    private String numeroIdentificacion;

    @Column(name = "ID_Departamento",nullable = false)
    private int idDepartamento;

    @Column(name = "ID_Provincia",nullable = false)
    private int idProvincia;

    @Column(name = "ID_Distrito",nullable = false)
    private int idDistrito;

    @Column(name = "ID_Prefijo",nullable = false)
    private int idPrefijo;

    @Column(name = "FechaRegistro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "cliente")
    private List<Lote> lotes;

}
