package com.cramirez.backendcramirez.documento.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TipoDocumento")
@Getter
@Setter
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoDocumento")
    private int idTipoDocumento;

    @Column(name = "TipoDocumento", nullable = false)
    private String tipoDocumento;

}
