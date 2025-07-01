package com.cramirez.backendcramirez.lote.infrastructure.repository;

import com.cramirez.backendcramirez.lote.domain.entity.Cuota;

import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Integer> {
    Optional<Cuota> findByIdLote(int idLote);
}
