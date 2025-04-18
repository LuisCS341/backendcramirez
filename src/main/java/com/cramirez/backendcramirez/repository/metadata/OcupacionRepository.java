package com.cramirez.backendcramirez.repository.metadata;

import com.cramirez.backendcramirez.entity.metadata.Ocupacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcupacionRepository extends JpaRepository<Ocupacion, Integer> {
}