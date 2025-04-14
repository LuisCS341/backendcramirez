package com.cramirez.backendcramirez.dto.empresa;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmpresaDTO {
    private int idEmpresa;
    private int idMoneda;
    private String nombreEmpresa;
    private String ruc;
    private String direccion;
    private String representanteLegal;
    private String dniVendedor;
    private String nPartidaEmpresa;
    private String nCuenta;
    private String cCI;
    private Date fechaEntrega;
    private String fechaFirmaContrato;
}
