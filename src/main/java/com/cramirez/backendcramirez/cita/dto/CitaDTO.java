package com.cramirez.backendcramirez.cita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private int idCita;
    private int idCliente;
    private String fechaCita;
    private String horaCita;
    private String personaAtiende;
}
