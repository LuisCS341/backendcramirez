package com.cramirez.backendcramirez.lote.infrastructure.repository;

import com.cramirez.backendcramirez.lote.domain.entity.Matriz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Integer> {
    Optional<Matriz> findByIdLote(int idLote);
}
