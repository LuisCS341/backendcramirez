package com.cramirez.backendcramirez.copropietario.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class CopropietarioConyugeDTO {

    private int idClienteCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idResidenciaCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDistritoCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idProvinciaCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idDepartamentoCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idNacionalidadCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idIdentificacionCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idCopropietarioConyuge;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperarioCopropietarioConyuge;

    private String nombresApellidosCopropietarioConyuge;
    private String direccionCopropietarioConyuge;
    private String numeroIdentificacionCopropietarioConyuge;
    private String OperarioCopropietarioConyuge;
    private String PrefijoPaisCopropietarioConyuge;
    private String DocumentoIdentificacionCopropietarioConyuge;
    private String ocupacionCopropietarioConyuge;
    private String NacionalidadCopropietarioConyuge;
    private String ResidenciaCopropietarioConyuge;
    private String DepartamentoCopropietarioConyuge;
    private String ProvinciaCopropietarioConyuge;
    private String DistritoCopropietarioConyuge;


}
