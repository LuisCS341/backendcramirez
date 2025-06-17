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

        private int idCliente;
        private int idClienteConyuge;
        private int idNacionalidadConyuge;
        private int idPrefijoConyuge;
        private int idIdentificacionConyuge;
        private int idResidenciaConyuge;
        private int idOperarioConyuge;
        private int idDepartamentoConyuge;
        private int idProvinciaConyuge;
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
