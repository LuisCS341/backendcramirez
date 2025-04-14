package com.cramirez.backendcramirez.repository.metadata;

import com.cramirez.backendcramirez.entity.metadata.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
}
