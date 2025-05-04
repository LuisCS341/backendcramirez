package com.cramirez.backendcramirez.financiamiento.dto;
import lombok.Data;

@Data
public class FinanciamientoDTO {
    private int idFinanciamiento;
    private int idContrato;
    private Float separacion;
    private int cantidadCuotas;
    private Float montoCuota;
    private int cuotasPendientes;
    private int diaPago;
}
