package com.cramirez.backendcramirez.matriz.infrastructure.repository;
import com.cramirez.backendcramirez.matriz.domain.entity.Matriz;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Integer> {

    List<Matriz> findByIdLote(int idLote);
}
