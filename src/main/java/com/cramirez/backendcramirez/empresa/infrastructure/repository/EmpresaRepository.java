package com.cramirez.backendcramirez.empresa.infrastructure.repository;
import com.cramirez.backendcramirez.empresa.domain.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
