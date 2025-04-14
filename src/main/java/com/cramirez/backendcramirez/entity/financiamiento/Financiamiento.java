package com.cramirez.backendcramirez.entity.financiamiento;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Financiamiento")
@Data
public class Financiamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Financiamiento")
    private int idFinanciamiento;

    @Column(name = "ID_Contraro", nullable = false)
    private int idContrato;

    @Column(name = "Separacion", nullable = false)
    private Float separacion;

    @Column(name = "Cantidad_Cuotas", nullable = false)
    private int cantidadCuotas;

    @Column(name = "Monto_Cuota", nullable = false)
    private Float montoCuota;

    @Column(name = "Cuotas_Pendientes", nullable = false)
    private int cuotasPendientes;

    @Column(name = "Dia_Pago", nullable = false)
    private int diaPago;


}

