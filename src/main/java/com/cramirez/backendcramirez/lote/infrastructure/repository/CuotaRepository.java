package com.cramirez.backendcramirez.lote.infrastructure.repository;

import com.cramirez.backendcramirez.lote.domain.entity.Cuota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Integer> {
    List<Cuota> findByIdLote(int idLote);
}
