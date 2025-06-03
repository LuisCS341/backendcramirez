package com.cramirez.backendcramirez.auth.application.service;
import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
import com.cramirez.backendcramirez.auth.dto.CredencialesDTO;
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

    public Optional<CredencialesDTO> encontrarPorUsuario(String usuario) {
        return credencialesRepository.findByUsuario(usuario)
                .map(this::convertirACredencialesDTO);
    }

    @Transactional
    public void actualizarCredenciales(CredencialesDTO credencialesDTO) {
        Credenciales entidad = convertirACredenciales(credencialesDTO);
        credencialesRepository.save(entidad);
    }

    public CredencialesDTO convertirACredencialesDTO(Credenciales entity) {
        CredencialesDTO dto = new CredencialesDTO();
        dto.setIdCredencial(entity.getIdCredencial());
        dto.setNombre(entity.getNombre());
        dto.setUsuario(entity.getUsuario());
        dto.setContrasena(entity.getContrasena());
        dto.setIdTipoIdentidad(entity.getIdTipoIdentidad());
        dto.setIdOperario(entity.getIdOperario());
        dto.setEmailUsuario(entity.getEmailUsuario());
        return dto;
    }

    public Credenciales convertirACredenciales(CredencialesDTO dto) {
        Credenciales entity = new Credenciales();
        entity.setIdCredencial(dto.getIdCredencial());
        entity.setNombre(dto.getNombre());
        entity.setUsuario(dto.getUsuario());
        entity.setContrasena(dto.getContrasena());
        entity.setIdTipoIdentidad(dto.getIdTipoIdentidad());
        entity.setIdOperario(dto.getIdOperario());
        entity.setEmailUsuario(dto.getEmailUsuario());
        return entity;
    }
}
