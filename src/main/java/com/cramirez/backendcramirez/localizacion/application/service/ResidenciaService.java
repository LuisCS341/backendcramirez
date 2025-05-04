package com.cramirez.backendcramirez.localizacion.application.service;

import com.cramirez.backendcramirez.dto.localizacion.ResidenciaDTO;
import com.cramirez.backendcramirez.entity.localizacion.Residencia;
import com.cramirez.backendcramirez.repository.localizacion.ResidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResidenciaService {

    private final ResidenciaRepository residenciaRepository;

    @Autowired
    public ResidenciaService(ResidenciaRepository residenciaRepository) {
        this.residenciaRepository = residenciaRepository;
    }

    public List<ResidenciaDTO> getAllResidencias() {
        return residenciaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResidenciaDTO getResidenciaById(int id) {
        Optional<Residencia> residencia = residenciaRepository.findById(id);
        return residencia.map(this::convertToDTO).orElse(null);
    }

    public ResidenciaDTO createResidencia(ResidenciaDTO residenciaDTO) {
        Residencia residencia = convertToEntity(residenciaDTO);
        Residencia savedResidencia = residenciaRepository.save(residencia);
        return convertToDTO(savedResidencia);
    }

    private ResidenciaDTO convertToDTO(Residencia residencia) {
        ResidenciaDTO dto = new ResidenciaDTO();
        dto.setIdResidencia(residencia.getIdResidencia());
        dto.setResidencia(residencia.getResidencia());
        return dto;
    }

    private Residencia convertToEntity(ResidenciaDTO dto) {
        Residencia residencia = new Residencia();
        residencia.setIdResidencia(dto.getIdResidencia());
        residencia.setResidencia(dto.getResidencia());
        return residencia;
    }
}
