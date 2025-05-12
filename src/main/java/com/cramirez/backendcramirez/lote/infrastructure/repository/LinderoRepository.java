package com.cramirez.backendcramirez.lote.infrastructure.repository;
import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import com.cramirez.backendcramirez.lote.dto.LinderoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinderoRepository extends JpaRepository<Lindero, Integer> {

    Optional<Lindero> findByIdLote(int idLote);
}
