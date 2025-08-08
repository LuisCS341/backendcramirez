package com.cramirez.backendcramirez.cliente.dto;

import com.cramirez.backendcramirez.copropietario.dto.CopropietarioConyugeDTO;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class ClienteDTO {
    private int idIdentificacion;
    private int idNacionalidad;
    private int idResidencia;
    private int idDepartamento;
    private int idProvincia;
    private int idDistrito;
    private int idPrefijo;
    private int idEstadoCivil;
    private int idCliente;
    private int idClienteClone;
    private int idOperario;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaRegistro;


    private String numeroIdentificacion;
    private String celularCliente;
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
    private String descripcionEstadoCivil;

    private List<CopropietarioDTO> copropietarios;
    private ClienteConyugeDTO conyuge;
    private List<CopropietarioConyugeDTO> copropietarioconyuge;
}
