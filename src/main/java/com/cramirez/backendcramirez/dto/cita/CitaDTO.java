package com.cramirez.backendcramirez.dto.cita;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private int idCita;
    private int idCliente;
    private int idEstado;
    private LocalDateTime fechaCita;
}
