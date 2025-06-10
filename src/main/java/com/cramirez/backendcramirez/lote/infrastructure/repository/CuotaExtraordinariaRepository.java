package com.cramirez.backendcramirez.lote.infrastructure.repository;

import com.cramirez.backendcramirez.lote.domain.entity.CuotaExtraordinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaExtraordinariaRepository extends JpaRepository<CuotaExtraordinaria, Integer> {

    List<CuotaExtraordinaria> findByIdLote(int idLote);
}
