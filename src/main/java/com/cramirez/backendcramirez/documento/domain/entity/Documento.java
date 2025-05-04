package com.cramirez.backendcramirez.documento.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Documento")
@Getter
@Setter
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Documento")
    private int idDocumento;

    @Column(name = "ID_TipoDocumento", nullable = false)
    private int idTipoDocumento;

    @Column(name = "Ruta_Archivo", nullable = false)
    private String rutaArchivo;

    @Column(name = "Fecha_Subida", nullable = false)
    private LocalDateTime fechaSubida;

}
