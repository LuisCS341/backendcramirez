package com.cramirez.backendcramirez.financiamiento.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private Long id;
    private Long tipoId;
    private Long contratoId;
    private Long monedaId;
    private String monto;
    private String cantidadCuotas;
    private String montoCuotas;
    private String cuotasExtraordinarias;
    private String mantenimientoMensual;
}
