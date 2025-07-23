package com.cramirez.backendcramirez.lote.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CuotaExtraordinaria")
@Getter
@Setter
public class CuotaExtraordinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CuotaExtraordinaria")
    private int idCuotaExtraordinaria;

    @Column(name = "ID_Lote")
    private int idLote;

    @Column(name = "CantidadCuotaExtraordinaria")
    private Float cantidadCuotaExtraordinaria;

    @Column(name = "MontoCuotaExtraordinaria")
    private Float montoCuotaExtraordinaria;

}
