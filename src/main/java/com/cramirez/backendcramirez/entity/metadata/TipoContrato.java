package com.cramirez.backendcramirez.entity.metadata;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TipoContrato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TipoContrato")
    private int idTipoContrato;

    @Column(name = "Tipo_Contrato")
    private String tipoContrato;
}

