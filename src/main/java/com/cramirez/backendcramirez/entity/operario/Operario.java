package com.cramirez.backendcramirez.entity.operario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Operario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Operario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Operario")
    private int idOperario;

    @Column(name = "id_Tipo_Identidad")
    private int tipoIdentidad;

    @Column(name = "Tipo_Operario")
    private String tipoOperario;
}
