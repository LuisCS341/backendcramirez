package com.cramirez.backendcramirez.entity.cita;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cita")
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cita", nullable = false)
    private int idCita;

    @Column(name = "ID_Cliente", nullable = false)
    private int idCliente;

    @Column(name = "ID_Estado", nullable = false)
    private int idEstado;

    @Column(name = "Fecha_Cita", nullable = false)
    private LocalDateTime fechaCita;

}
