package com.cramirez.backendcramirez.cliente.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private int idClienteConyuge;
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

        private String operarioConyuge;
        private String prefijoPaisConyuge;
        private String documentoIdentificacionConyuge;
        private String ocupacionConyuge;
        private String nacionalidadConyuge;
        private String residenciaConyuge;
        private String departamentoConyuge;
        private String provinciaConyuge;
        private String distritoConyuge;
}
