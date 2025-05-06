package com.cramirez.backendcramirez.localizacion.infrastructure.repository;
import com.cramirez.backendcramirez.localizacion.domain.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findByNombreDepartamento(String nombreDepartamento);
}
