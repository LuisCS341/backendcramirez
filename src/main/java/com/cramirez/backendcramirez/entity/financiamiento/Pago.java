package com.cramirez.backendcramirez.entity.financiamiento;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Pago")
    private Long id;

    @Column(name = "ID_TipoPago", nullable = false)
    private Long tipoId;

    @Column(name = "ID_Moneda", nullable = false)
    private Long monedaId;

    @Column(name = "ID_Contrato", nullable = false)
    private Long contratoId;

    @Column(name = "Monto")
    private String monto;

    @Column(name = "Cantidad_Cuotas")
    private String cantidadCuotas;

    @Column(name = "Monto_Cuotas")
    private String montoCuotas;

    @Column(name = "Cuotas_Extraordinarias")
    private String cuotasExtraordinarias;

    @Column(name = "Mantenimiento_Mensual", nullable = false)
    private String mantenimientoMensual;
}
