package com.cramirez.backendcramirez.copropietario.dto;

import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CopropietarioDTO {
    private int idCopropietario;
    private int idClienteCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idResidenciaCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDepartamentoCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idProvinciaCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperarioCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDistritoCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idNacionalidadCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idEstadoCivilCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idIdentificacionCopropietarios;

    private String nombresApellidosCopropietarios;
    private String direccionCopropietarios;
    private String numeroIdentificacionCopropietarios;
    private String descripcionEstadoCivilCopropietarios;

    private String operarioCopropietarios;
    private String documentoIdentificacionCopropietarios;
    private String estadoCivilCopropietarios;
    private String ocupacionCopropietarios;
    private String nacionalidadCopropietarios;
    private String residenciaCopropietarios;
    private String departamentoCopropietarios;
    private String provinciaCopropietarios;
    private String distritoCopropietarios;

}
