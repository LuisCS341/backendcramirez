package com.cramirez.backendcramirez.financiamiento.infrastructure.repository;
import com.cramirez.backendcramirez.financiamiento.domain.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}

