package com.cramirez.backendcramirez.entity.localizacion;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Distritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Distrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Distrito")
    private int idDistrito;

    @Column(name = "Nombre_Distrito")
    private String nombreDistrito;

    @Column(name = "ID_Provincia", nullable = false) // Relación con Provincia
    private int idProvincia;
}