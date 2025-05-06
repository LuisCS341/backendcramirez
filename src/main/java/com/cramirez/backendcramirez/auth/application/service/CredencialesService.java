package com.cramirez.backendcramirez.auth.application.service;
import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
import com.cramirez.backendcramirez.auth.infrastructure.repository.CredencialesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CredencialesService {

    private final CredencialesRepository credencialesRepository;

    public CredencialesService(CredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    public Optional<Credenciales> encontrarPorUsuario(String usuario) {
        return credencialesRepository.findByUsuario(usuario);
    }

    @Transactional
    public void actualizarCredenciales(Credenciales credenciales) {
        credencialesRepository.save(credenciales);
    }
}
