package com.cramirez.backendcramirez.cliente.infrastructure.repository;

import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteConyugeRepository extends JpaRepository<ClienteConyuge, Integer> {
    Optional<ClienteConyuge> findByIdCliente(Integer idCliente);
}
