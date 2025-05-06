package com.cramirez.backendcramirez.proyecto.infrastructure.repository;
import com.cramirez.backendcramirez.proyecto.domain.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}
