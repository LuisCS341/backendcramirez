package com.cramirez.backendcramirez.localizacion.infrastructure.repository;

import com.cramirez.backendcramirez.entity.localizacion.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
}
