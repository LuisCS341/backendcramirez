package com.cramirez.backendcramirez.documento.application.service;
import com.cramirez.backendcramirez.documento.infrastructure.repository.TipoDocumentoRepository;
import com.cramirez.backendcramirez.entity.documento.TipoDocumento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoService {
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoService(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    public List<TipoDocumento> findAll() {
        return tipoDocumentoRepository.findAll();
    }

    public Optional<TipoDocumento> findById(int id) {
        return tipoDocumentoRepository.findById(id);
    }

    public TipoDocumento save(TipoDocumento tipoDocumento) {
        return tipoDocumentoRepository.save(tipoDocumento);
    }

    public void deleteById(int id) {
        tipoDocumentoRepository.deleteById(id);
    }
}
