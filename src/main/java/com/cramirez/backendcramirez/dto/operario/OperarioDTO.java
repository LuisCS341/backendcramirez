package com.cramirez.backendcramirez.dto.operario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperarioDTO {
    private int idOperario;
    private int tipoIdentidad;
    private String tipoOperario;
}
