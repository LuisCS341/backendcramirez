package com.cramirez.backendcramirez.repository.proyecto;

import com.cramirez.backendcramirez.entity.proyecto.TipoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProyectoRepository extends JpaRepository<TipoProyecto, Integer> {
}
