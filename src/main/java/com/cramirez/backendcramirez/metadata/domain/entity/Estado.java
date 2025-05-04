package com.cramirez.backendcramirez.metadata.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Estado")
@Data
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estado",nullable = false)
    private int idEstado;

    @Column(name="Estado",nullable = false)
    private String Estado;

}
