package com.cramirez.backendcramirez.metadata.infrastructure.repository;

import com.cramirez.backendcramirez.entity.metadata.Nacionalidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NacionalidadRepository extends JpaRepository<Nacionalidad, Integer> {
}
