package com.cramirez.backendcramirez.auth.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredencialesDTO {

    private Long idCredencial;
    private String nombre;
    private String usuario;
    private String contrasena;
    private int idTipoIdentidad;
    private int idOperario;
    private String emailUsuario;
}
