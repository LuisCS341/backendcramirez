package com.cramirez.backendcramirez.repository.auth;

import com.cramirez.backendcramirez.entity.auth.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredencialesRepository extends JpaRepository<Credenciales, Integer> {
    Optional<Credenciales> findByUsuario(String usuario);
}
