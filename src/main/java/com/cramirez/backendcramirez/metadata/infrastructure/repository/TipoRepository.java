package com.cramirez.backendcramirez.metadata.infrastructure.repository;
import com.cramirez.backendcramirez.metadata.domain.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {
}
