package com.cramirez.backendcramirez.cita.infrastructure.repository;

import com.cramirez.backendcramirez.cita.domain.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

}
