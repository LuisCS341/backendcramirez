package com.cramirez.backendcramirez.localizacion.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Provincias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Provincia")
    private int idProvincia;

    @Column(name = "Nombre_Provincia", nullable = false)
    private String nombreProvincia;

    @Column(name = "ID_Departamento", nullable = false)
    private int idDepartamento;
}
