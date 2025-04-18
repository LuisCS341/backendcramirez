package com.cramirez.backendcramirez.repository.financiamiento;

import com.cramirez.backendcramirez.entity.financiamiento.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}

