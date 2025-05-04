package com.cramirez.backendcramirez.operario.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperarioDTO {
    private int idOperario;
    private int tipoIdentidad;
    private String tipoOperario;
}
