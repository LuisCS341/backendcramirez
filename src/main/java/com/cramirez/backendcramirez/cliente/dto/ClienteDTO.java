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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idIdentificacion;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idNacionalidad;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idResidencia;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDepartamento;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idProvincia;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDistrito;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idPrefijo;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
    private String Operario;
    private String PrefijoPais;
    private String DocumentoIdentificacion;
    private String EstadoCivil;
    private String ocupacion;
    private String Nacionalidad;
    private String Residencia;
    private String Departamento;
    private String Provincia;
    private String Distrito;

}
