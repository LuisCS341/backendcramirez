package com.cramirez.backendcramirez.repository.cita;

import com.cramirez.backendcramirez.entity.cita.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

}
