package com.cramirez.backendcramirez.cliente.infrastructure.repository;


import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findById(Integer id);

    List<Cliente> findByIdOperario(int idOperario);


    List<Cliente> findByNumeroIdentificacion(String numeroIdentificacion);


    boolean existsByNumeroIdentificacion(String numeroIdentificacion);


}
