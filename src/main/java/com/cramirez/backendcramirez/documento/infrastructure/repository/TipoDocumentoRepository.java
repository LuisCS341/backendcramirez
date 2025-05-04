package com.cramirez.backendcramirez.documento.infrastructure.repository;

import com.cramirez.backendcramirez.entity.documento.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository  extends JpaRepository<TipoDocumento, Integer> {
}
