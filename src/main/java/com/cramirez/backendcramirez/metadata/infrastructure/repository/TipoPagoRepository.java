package com.cramirez.backendcramirez.metadata.infrastructure.repository;
import com.cramirez.backendcramirez.metadata.domain.entity.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
}
