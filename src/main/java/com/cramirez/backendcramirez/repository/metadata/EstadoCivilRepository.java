package com.cramirez.backendcramirez.repository.metadata;

import com.cramirez.backendcramirez.entity.metadata.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {
}
