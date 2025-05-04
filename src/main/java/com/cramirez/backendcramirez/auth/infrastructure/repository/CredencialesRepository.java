package com.cramirez.backendcramirez.auth.infrastructure.repository;

import com.cramirez.backendcramirez.entity.auth.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredencialesRepository extends JpaRepository<Credenciales, Integer> {
    Optional<Credenciales> findByUsuario(String usuario);
}
