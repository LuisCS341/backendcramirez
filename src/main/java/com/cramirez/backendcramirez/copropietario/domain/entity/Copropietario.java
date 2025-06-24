package com.cramirez.backendcramirez.copropietario.domain.entity;

import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Copropietario")
@Getter
@Setter
public class Copropietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Copropietario",nullable = false)
    private int idCopropietario;

    @Column(name = "ID_Cliente",nullable = false)
    private int idClienteCopropietarios;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "ID_Cliente", insertable = false, updatable = false)
    private Cliente cliente;


    @Column(name = "ID_Residencia",nullable = false)
    private int idResidenciaCopropietarios;

    @Column(name = "ID_Prefijo",nullable = false)
    private int idPrefijoCopropietarios;

    @Column(name = "ID_Departamento",nullable = false)
    private int idDepartamentoCopropietarios;

    @Column(name = "ID_Provincia",nullable = false)
    private int idProvinciaCopropietarios;

    @Column(name = "ID_Operario",nullable = false)
    private int idOperarioCopropietarios;


    @Column(name = "ID_Distrito",nullable = false)
    private int idDistritoCopropietarios;

    @Column(name = "ID_Nacionalidad",nullable = false)
    private int idNacionalidadCopropietarios;

    @Column(name = "ID_EstadoCivil",nullable = false)
    private int idEstadoCivilCopropietarios;

    @Column(name = "ID_Identificacion",nullable = false)
    private int idIdentificacionCopropietarios;

    @Column(name = "Nombres_Apellidos",nullable = false)
    private String nombresApellidosCopropietarios;

    @Column(name = "Direccion",nullable = false)
    private String direccionCopropietarios;

    @Column(name = "Correo_Electronico",nullable = false)
    private String correoElectronicoCopropietarios;

    @Column(name = "Celular",nullable = false)
    private String celularCopropietarios;

    @Column(name = "Ocupacion",nullable = false)
    private String ocupacionCopropietarios;

    @Column(name = "NumeroIdentificacion",nullable = false  )
    private String numeroIdentificacionCopropietarios;

    @Column(name = "DescripcionEstadoCivil",nullable = false  )
    private String descripcionEstadoCivilCopropietarios;
}
