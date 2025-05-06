package com.cramirez.backendcramirez.copropietario.infrastructure.repository;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopropietarioRepository extends JpaRepository<Copropietario, Integer> {
}
