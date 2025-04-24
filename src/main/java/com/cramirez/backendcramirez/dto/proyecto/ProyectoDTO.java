package com.cramirez.backendcramirez.dto.proyecto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

