package com.cramirez.backendcramirez.localizacion.infrastructure.repository;
import com.cramirez.backendcramirez.localizacion.domain.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
}
