package com.cramirez.backendcramirez.metadata.infrastructure.repository;

import com.cramirez.backendcramirez.entity.metadata.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
