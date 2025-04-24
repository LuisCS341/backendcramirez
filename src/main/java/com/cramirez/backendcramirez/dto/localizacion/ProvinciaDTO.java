package com.cramirez.backendcramirez.dto.localizacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaDTO {
    private int idProvincia;
    private int idDepartamento;
    private String nombreProvincia;
}
