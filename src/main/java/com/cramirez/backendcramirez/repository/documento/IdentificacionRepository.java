package com.cramirez.backendcramirez.repository.documento;

import com.cramirez.backendcramirez.entity.documento.Identificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificacionRepository extends JpaRepository<Identificacion, Integer> {
}
