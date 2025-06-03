package com.cramirez.backendcramirez.auth.domain.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Credenciales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID_Credencial")
    private Long idCredencial;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Usuario",unique = true, nullable = false)
    private String usuario;

    @Column(name ="Contrasena",nullable = false)
    private String contrasena;

    @Column(name = "ID_Tipo_Identidad", nullable = false)
    private int idTipoIdentidad;

    @Column(name = "ID_Operario", nullable = false)
    private int idOperario;

    @Column(name ="EmailUsuario",nullable = false)
    private String emailUsuario;

}
