package com.cramirez.backendcramirez.contrato.infrastructure.repository;
import com.cramirez.backendcramirez.contrato.domain.entity.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
}
