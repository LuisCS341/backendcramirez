package com.cramirez.backendcramirez.dto.proyecto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDTO {
    private int idProyecto;
    private int idEmpresa;
    private int idMatriz;
    private String nombreProyecto;
}

