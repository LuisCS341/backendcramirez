package com.cramirez.backendcramirez.cliente.dto;
import com.cramirez.backendcramirez.lote.dto.LoteDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ClienteConLotesDTO {

    private ClienteDTO cliente; // Campo que contiene la información del cliente
    private List<LoteDTO> lotes; // Lista que contiene los lotes asociados al cliente

    // Constructor sin argumentos (requerido por Jackson para deserialización)
    public ClienteConLotesDTO() {}

    public ClienteConLotesDTO(ClienteDTO cliente, List<LoteDTO> lotes) {
        this.cliente = cliente;
        this.lotes = lotes;
    }
}

