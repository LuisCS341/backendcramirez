package com.cramirez.backendcramirez.entity.metadata;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "EstadoCivil")
@Getter
@Setter
public class EstadoCivil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EstadoCivil")
    private int idEstadoCivil;

    @Column(name = "EstadoCivil", nullable = false, length = 50)
    private String EstadoCivil;
}
