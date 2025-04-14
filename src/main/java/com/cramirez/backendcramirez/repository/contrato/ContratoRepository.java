package com.cramirez.backendcramirez.repository.contrato;

import com.cramirez.backendcramirez.entity.contrato.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
}
