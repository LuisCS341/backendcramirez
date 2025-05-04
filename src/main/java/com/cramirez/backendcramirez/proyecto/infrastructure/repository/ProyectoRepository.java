package com.cramirez.backendcramirez.proyecto.infrastructure.repository;

import com.cramirez.backendcramirez.entity.proyecto.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
}
