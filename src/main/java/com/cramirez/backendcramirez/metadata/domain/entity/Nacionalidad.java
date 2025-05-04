package com.cramirez.backendcramirez.metadata.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Nacionalidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nacionalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Nacionalidad")
    private int idNacionalidad;

    @Column(name = "Nacionalidad")
    private String nombreNacionalidad;
}

