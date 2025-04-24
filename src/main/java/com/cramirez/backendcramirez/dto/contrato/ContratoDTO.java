package com.cramirez.backendcramirez.dto.contrato;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ContratoDTO {
    private int idContrato;
    private int idCliente;
    private int idTipoContrato;
    private int idLote;
    private String numeroContrato;
    private Date fechaInicio;
    private Date fechaCanelacion;
    private Float mantenimientoMensual;
    private String mantenimientoLetras;
}
