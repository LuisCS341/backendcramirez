package com.cramirez.backendcramirez.cliente.dto;
import com.cramirez.backendcramirez.lote.dto.LoteDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteConLotesDTO {

    private ClienteDTO cliente;
    private List<LoteDTO> lotes;
}
