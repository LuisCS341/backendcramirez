package com.cramirez.backendcramirez.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@JsonPropertyOrder({
        "idCliente",
        "nombresApellidos",
        "ocupacion",
        "documentoIdentificacion",
        "numeroIdentificacion",
        "nacionalidad",
        "residencia",
        "direccion",
        "departamento",
        "provincia",
        "distrito",
        "correoElectronico",
        "prefijoPais",
        "celularCliente",
        "estadoCivil",
        "operario",
        "fechaRegistro"
})
public class ClienteDTO {
    private int idIdentificacion;
    private int idNacionalidad;
    private int idResidencia;
    private int idDepartamento;
    private int idProvincia;
    private int idDistrito;
    private int idPrefijo;
    private int idEstadoCivil;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int idCliente;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperario;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaRegistro;


    private String numeroIdentificacion;
    private int celularCliente;
    private String direccion;
    private String correoElectronico;
    private String nombresApellidos;
    private String operario;
    private String prefijoPais;
    private String documentoIdentificacion;
    private String estadoCivil;
    private String ocupacion;
    private String nacionalidad;
    private String residencia;
    private String departamento;
    private String provincia;
    private String distrito;

}
