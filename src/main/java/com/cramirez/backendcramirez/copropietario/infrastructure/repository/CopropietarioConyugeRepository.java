package com.cramirez.backendcramirez.copropietario.infrastructure.repository;

import com.cramirez.backendcramirez.entity.copropietario.CopropietarioConyuge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopropietarioConyugeRepository extends JpaRepository<CopropietarioConyuge, Integer> {
}
