package com.cramirez.backendcramirez.proyecto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TipoProyecto")
public class TipoProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoProyecto")
    private int idTipoProyecto;
    @Column(name = "TipoProyecto", nullable = false)
    private String tipoProyecto;

}
