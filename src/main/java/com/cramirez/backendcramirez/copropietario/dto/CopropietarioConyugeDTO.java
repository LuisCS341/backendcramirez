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

    private int idResidenciaCopropietarioConyuge;

    private int idDistritoCopropietarioConyuge;

    private int idProvinciaCopropietarioConyuge;

    private int idDepartamentoCopropietarioConyuge;

    private int idNacionalidadCopropietarioConyuge;

    private int idIdentificacionCopropietarioConyuge;

    private int idCopropietarioConyuge;

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
