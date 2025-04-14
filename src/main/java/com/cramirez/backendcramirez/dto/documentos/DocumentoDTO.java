package com.cramirez.backendcramirez.dto.documentos;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DocumentoDTO {
    private int idDocumento;
    private int idTipoDocumento;
    private String rutaArchivo;
    private LocalDateTime fechaSubida;
}
