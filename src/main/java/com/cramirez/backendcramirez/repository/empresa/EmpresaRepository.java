package com.cramirez.backendcramirez.repository.empresa;

import com.cramirez.backendcramirez.entity.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
