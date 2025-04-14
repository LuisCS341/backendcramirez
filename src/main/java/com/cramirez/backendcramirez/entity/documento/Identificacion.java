package com.cramirez.backendcramirez.entity.documento;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Identificacion")
@Data
public class Identificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Identificacion")
    private int idDocumentoIdentificacion;

    @Column(name = "DocumentoIdentificacion",nullable = false)
    private String documentoIdentificacion;
}
