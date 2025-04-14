package com.cramirez.backendcramirez.repository.operario;

import com.cramirez.backendcramirez.entity.operario.Operario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperarioRepository extends JpaRepository<Operario, Integer> {
}
