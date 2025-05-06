package com.cramirez.backendcramirez.documento.infrastructure.repository;
import com.cramirez.backendcramirez.documento.domain.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository  extends JpaRepository<TipoDocumento, Integer> {
}
