package com.cramirez.backendcramirez.localizacion.infrastructure.repository;
import com.cramirez.backendcramirez.localizacion.domain.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
}
