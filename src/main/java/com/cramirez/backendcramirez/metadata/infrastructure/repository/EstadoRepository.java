package com.cramirez.backendcramirez.metadata.infrastructure.repository;

import com.cramirez.backendcramirez.entity.metadata.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
