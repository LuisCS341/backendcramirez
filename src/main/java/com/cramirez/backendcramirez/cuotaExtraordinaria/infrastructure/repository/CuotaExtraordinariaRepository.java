package com.cramirez.backendcramirez.cuotaExtraordinaria.infrastructure.repository;

import com.cramirez.backendcramirez.cuotaExtraordinaria.domain.entity.CuotaExtraordinaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuotaExtraordinariaRepository extends JpaRepository<CuotaExtraordinaria, Integer> {

}
