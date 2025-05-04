package com.cramirez.backendcramirez.metadata.infrastructure.repository;

import com.cramirez.backendcramirez.entity.metadata.TipoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEntidadRepository extends JpaRepository<TipoEntidad, Long> {
}

