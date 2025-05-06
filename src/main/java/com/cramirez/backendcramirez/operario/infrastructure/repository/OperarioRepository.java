package com.cramirez.backendcramirez.operario.infrastructure.repository;
import com.cramirez.backendcramirez.operario.domain.entity.Operario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperarioRepository extends JpaRepository<Operario, Integer> {
}
