package com.cramirez.backendcramirez.metadata.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Prefijotelefonico")
@Getter
@Setter
public class Prefijotelefonico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Prefijo")
    private int idPrefijo;
    @Column(name = "PrefijoPais")
    private String prefijoPais;


}
