package com.cramirez.backendcramirez.cuotaExtraordinaria.infrastructure.repository;

import com.cramirez.backendcramirez.cuotaExtraordinaria.domain.entity.CuotaExtraordinaria;
import com.cramirez.backendcramirez.cuotaExtraordinaria.dto.CuotaExtraordinariaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaExtraordinariaRepository extends JpaRepository<CuotaExtraordinaria, Integer> {

    List<CuotaExtraordinaria> findByIdLote(int idLote);
}
