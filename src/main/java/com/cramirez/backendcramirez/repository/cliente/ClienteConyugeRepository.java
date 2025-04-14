package com.cramirez.backendcramirez.repository.cliente;

import com.cramirez.backendcramirez.entity.cliente.ClienteConyuge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteConyugeRepository extends JpaRepository<ClienteConyuge, Integer> {
}
