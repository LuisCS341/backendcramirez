package com.cramirez.backendcramirez.repository.copropietario;

import com.cramirez.backendcramirez.entity.copropietario.Copropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopropietarioRepository extends JpaRepository<Copropietario, Integer> {
}
