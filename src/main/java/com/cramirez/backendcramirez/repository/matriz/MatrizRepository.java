package com.cramirez.backendcramirez.repository.matriz;

import com.cramirez.backendcramirez.entity.matriz.Matriz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatrizRepository extends JpaRepository<Matriz, Integer> {
}
