package com.cramirez.backendcramirez.lote.dto;
import lombok.Data;

@Data
public class DocumentoDTO {


    private int idDocumento;
    private int idLote;
    private String cartaNoAdeudo;
    private String certificadoLote;
    private String plano1;
    private String plano2;
    private String envioMinuta;
    private String fechaCita;
    private String horaCita;
    private String modificarMinuta;
    private String minutaEscaneada;
    private String expNotaria;
}
