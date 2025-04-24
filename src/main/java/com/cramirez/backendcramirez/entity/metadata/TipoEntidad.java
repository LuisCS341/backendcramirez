package com.cramirez.backendcramirez.entity.metadata;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
