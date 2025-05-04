package com.cramirez.backendcramirez.cliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonPropertyOrder({
        "nombresApellidosConyuge",
        "ocupacionConyuge",
        "documentoIdentificacionConyuge",
        "numeroIdentificacionConyuge",
        "nacionalidadConyuge",
        "residenciaConyuge",
        "direccionConyuge",
        "correoElectronicoConyuge",
        "prefijoPaisConyuge",
        "celularConyuge",
        "departamentoConyuge",
        "provinciaConyuge",
        "distritoConyuge",
        "operarioConyuge"
})

public class ClienteConyugeDTO {

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idCliente;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idNacionalidadConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idPrefijoConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idIdentificacionConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idResidenciaConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idOperarioConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idDepartamentoConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idProvinciaConyuge;
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private int idDistritoConyuge;


        private String nombresApellidosConyuge;
        private String direccionConyuge;
        private String correoElectronicoConyuge;
        private String celularConyuge;
        private String numeroIdentificacionConyuge;

        private String OperarioConyuge;
        private String PrefijoPaisConyuge;
        private String DocumentoIdentificacionConyuge;
        private String ocupacionConyuge;
        private String NacionalidadConyuge;
        private String ResidenciaConyuge;
        private String DepartamentoConyuge;
        private String ProvinciaConyuge;
        private String DistritoConyuge;
}
