package com.cramirez.backendcramirez.lote.infrastructure.repository;

import com.cramirez.backendcramirez.entity.lote.Lindero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinderoRepository extends JpaRepository<Lindero, Integer> {
}
