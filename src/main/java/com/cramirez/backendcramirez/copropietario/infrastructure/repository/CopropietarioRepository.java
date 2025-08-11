package com.cramirez.backendcramirez.copropietario.infrastructure.repository;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopropietarioRepository extends JpaRepository<Copropietario, Integer> {
    List<Copropietario> findByIdClienteCopropietarios(int idCliente);
}
