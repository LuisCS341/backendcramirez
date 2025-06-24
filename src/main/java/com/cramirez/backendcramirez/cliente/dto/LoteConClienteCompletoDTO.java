package com.cramirez.backendcramirez.cliente.dto;

import com.cramirez.backendcramirez.lote.dto.LoteDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteConClienteCompletoDTO {
    private ClienteDTO cliente;
    private LoteDTO lote;

}


