package com.cramirez.backendcramirez.localizacion.infrastructure.repository;
import com.cramirez.backendcramirez.entity.localizacion.Residencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenciaRepository extends JpaRepository<Residencia, Integer> {
}
