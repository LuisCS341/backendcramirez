package com.cramirez.backendcramirez.copropietario.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CopropietarioConyuge")
@Data
@Getter
@Setter
public class CopropietarioConyuge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CopropietarioConyuge")
    private int idCopropietarioConyuge;


    @Column(name = "ID_Cliente", nullable = false)
    private int idClienteCopropietarioConyuge;


    @Column(name = "ID_Residencia", nullable = false)
    private int idResidenciaCopropietarioConyuge;

    @Column(name = "ID_Operario")
    private int idOperarioCopropietarioConyuge;

    @Column(name = "ID_Prefijo", nullable = false)
    private int idPrefijoCopropietarioConyuge;


    @Column(name = "ID_Distrito", nullable = false)
    private int idDistritoCopropietarioConyuge;


    @Column(name = "ID_Provincia", nullable = false)
    private int idProvinciaCopropietarioConyuge;


    @Column(name = "ID_Departamento", nullable = false)
    private int idDepartamentoCopropietarioConyuge;


    @Column(name = "ID_Nacionalidad", nullable = false)
    private int idNacionalidadCopropietarioConyuge;


    @Column(name = "ID_Identificacion", nullable = false)
    private int idIdentificacionCopropietarioConyuge;

    @Column(name = "Nombres_Apellidos", nullable = false)
    private String nombresApellidosCopropietarioConyuge;

    @Column(name = "Direccion",nullable = false)
    private String direccionCopropietarioConyuge;

    @Column(name = "Correo_Electronico", nullable = false)
    private String correoElectronicoCopropietarioConyuge;

    @Column(name = "Celular",nullable = false)
    private String celularCopropietarioConyuge;

    @Column(name = "NumeroIdentificacion", nullable = false)
    private String numeroIdentificacionCopropietarioConyuge;

    @Column(name = "Ocupacion", nullable = false)
    private String ocupacionCopropietarioConyuge;

}
