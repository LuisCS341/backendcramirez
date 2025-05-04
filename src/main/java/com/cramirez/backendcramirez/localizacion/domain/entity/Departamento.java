package com.cramirez.backendcramirez.localizacion.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_Departamento")
    private Long idDepartamento;

    @Column(name = "Nombre_Departamento", nullable = false, unique = true)
    private String nombreDepartamento;


}

