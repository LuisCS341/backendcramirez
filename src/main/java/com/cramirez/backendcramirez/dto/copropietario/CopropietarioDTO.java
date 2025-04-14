package com.cramirez.backendcramirez.dto.copropietario;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({
        "nombresApellidosCopropietarios",
        "ocupacionCopropietarios",
        "documentoIdentificacionCopropietarios",
        "numeroIdentificacionCopropietarios",
        "nacionalidadCopropietarios",
        "residenciaCopropietarios",
        "direccionCopropietarios",
        "correoElectronicoCopropietarios",
        "prefijoPaisCopropietarios",
        "celularCopropietarios",
        "departamentoCopropietarios",
        "provinciaCopropietarios",
        "distritoCopropietarios",
        "estadoCivilCopropietarios",
        "operarioCopropietarios"
})
public class CopropietarioDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idCopropietario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idClienteCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idResidenciaCopropietarios;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idPrefijoCopropietarios;
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
    private String correoElectronicoCopropietarios;
    private String celularCopropietarios;
    private String numeroIdentificacionCopropietarios;

    private String OperarioCopropietarios;
    private String PrefijoPaisCopropietarios;
    private String DocumentoIdentificacionCopropietarios;
    private String EstadoCivilCopropietarios;
    private String ocupacionCopropietarios;
    private String NacionalidadCopropietarios;
    private String ResidenciaCopropietarios;
    private String DepartamentoCopropietarios;
    private String ProvinciaCopropietarios;
    private String DistritoCopropietarios;
}
