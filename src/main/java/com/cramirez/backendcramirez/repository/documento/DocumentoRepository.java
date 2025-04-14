package com.cramirez.backendcramirez.repository.documento;

import com.cramirez.backendcramirez.entity.documento.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}
