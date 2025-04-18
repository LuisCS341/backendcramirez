package com.cramirez.backendcramirez.dto.lote;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "nombreProyecto",
        "ubicacion",
        "manzana",
        "numeroLote",
        "contrato",
        "areaLote"
})
public class LoteDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idOperario;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idClienteLote;
//-----------------------------------
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoProyecto;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idUbicacion;
    private String manzana;
    private int numeroLote;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int idTipoContrato;
    private Float areaLote;
//-----------------------------------
    private String tipoProyecto;
    private String ubicacion;
    private String contrato;
}
