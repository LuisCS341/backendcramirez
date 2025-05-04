package com.cramirez.backendcramirez.metadata.application.service;

import com.cramirez.backendcramirez.dto.metadata.EstadoCivilDTO;
import com.cramirez.backendcramirez.entity.metadata.EstadoCivil;
import com.cramirez.backendcramirez.repository.metadata.EstadoCivilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoCivilService {

    private final EstadoCivilRepository estadoCivilRepository;

    @Autowired
    public EstadoCivilService(EstadoCivilRepository estadoCivilRepository) {
        this.estadoCivilRepository = estadoCivilRepository;
    }

    public List<EstadoCivilDTO> obtenerTodos() {
        return estadoCivilRepository.findAll().stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    public EstadoCivilDTO obtenerPorId(int id) {
        return estadoCivilRepository.findById(id)
                .map(this::convertirA_DTO)
                .orElse(null);
    }

    public EstadoCivilDTO crear(EstadoCivilDTO estadoCivilDTO) {
        EstadoCivil estadoCivil = convertirA_Entidad(estadoCivilDTO);
        EstadoCivil guardado = estadoCivilRepository.save(estadoCivil);
        return convertirA_DTO(guardado);
    }

    public EstadoCivilDTO actualizar(int id, EstadoCivilDTO estadoCivilDTO) {
        return estadoCivilRepository.findById(id)
                .map(estadoCivil -> {
                    estadoCivil.setIdEstadoCivil(estadoCivilDTO.getIdEstadoCivil());
                    estadoCivil.setEstadoCivil(estadoCivilDTO.getEstadoCivil());
                    EstadoCivil actualizado = estadoCivilRepository.save(estadoCivil);
                    return convertirA_DTO(actualizado);
                })
                .orElse(null);
    }

    public void eliminar(int id) {
        estadoCivilRepository.deleteById(id);
    }

    private EstadoCivilDTO convertirA_DTO(EstadoCivil estadoCivil) {
        EstadoCivilDTO dto = new EstadoCivilDTO();
        dto.setIdEstadoCivil(estadoCivil.getIdEstadoCivil());
        dto.setEstadoCivil(estadoCivil.getEstadoCivil());
        return dto;
    }

    private EstadoCivil convertirA_Entidad(EstadoCivilDTO dto) {
        EstadoCivil estadoCivil = new EstadoCivil();
        estadoCivil.setIdEstadoCivil(dto.getIdEstadoCivil());
        estadoCivil.setEstadoCivil(dto.getEstadoCivil());
        return estadoCivil;
    }
}
