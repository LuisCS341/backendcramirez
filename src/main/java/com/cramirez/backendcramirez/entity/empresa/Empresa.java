package com.cramirez.backendcramirez.entity.empresa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Empresa")
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Empresa")
    private int idEmpresa;
    @Column(name = "ID_Moneda")
    private int idMoneda;

    @Column(name = "Nombre_Empresa", nullable = false)
    private String nombreEmpresa;

    @Column(name = "RUC", nullable = false)
    private String ruc;

    @Column(name = "Direccion", nullable = false)
    private String direccion;

    @Column(name = "Representante_Legal", nullable = false)
    private String representanteLegal;
    @Column(name = " DNI_vendedor", nullable = false)
    private String dniVendedor;

    @Column(name = "N_PartidaEmpresa", nullable = false)
    private String nPartidaEmpresa;

    @Column(name = "Num_cuenta", nullable = false)
    private String nCuenta;

    @Column(name = "CCI", nullable = false)
    private String cCI;
    @Column(name = "Fecha_entrega", nullable = false)
    private Date fechaEntrega;
    @Column(name = "Fecha_Firma_Contrato", nullable = false)
    private String fechaFirmaContrato;
}
