package com.cramirez.backendcramirez.dto.financiamiento;
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
