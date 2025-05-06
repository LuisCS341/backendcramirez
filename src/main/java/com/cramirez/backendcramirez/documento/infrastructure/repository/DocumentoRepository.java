package com.cramirez.backendcramirez.documento.infrastructure.repository;
import com.cramirez.backendcramirez.documento.domain.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}
