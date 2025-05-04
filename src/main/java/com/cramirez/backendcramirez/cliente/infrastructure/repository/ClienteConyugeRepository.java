package com.cramirez.backendcramirez.cliente.infrastructure.repository;

import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteConyugeRepository extends JpaRepository<ClienteConyuge, Integer> {
}
