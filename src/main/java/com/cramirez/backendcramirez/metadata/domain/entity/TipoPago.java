package com.cramirez.backendcramirez.metadata.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TipoPago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoPago")
    private Long id;

    @Column(name = "Tipo_Pago", nullable = false)
    private String tipoPago;
}

