package com.cramirez.backendcramirez.entity.metadata;

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
