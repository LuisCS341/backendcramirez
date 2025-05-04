package com.cramirez.backendcramirez.proyecto.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Proyecto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Proyecto")
    private int idProyecto;
    @Column(name = "ID_Empresa", nullable = false)
    private int idEmpresa;
    @Column(name = "ID_Matriz", nullable = false)
    private int idMatriz;
    @Column(name = "Nombre_Proyecto", nullable = false)
    private String nombreProyecto;
}
