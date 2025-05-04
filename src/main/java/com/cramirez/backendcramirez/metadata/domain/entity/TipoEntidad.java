package com.cramirez.backendcramirez.metadata.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Tipo_Entidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tipo_Identidad")
    private Long id;

    @Column(name = "Tipo_Entidad")
    private String tipoEntidad;
}
