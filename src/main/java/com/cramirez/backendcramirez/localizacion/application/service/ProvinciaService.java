package com.cramirez.backendcramirez.localizacion.application.service;

import com.cramirez.backendcramirez.dto.localizacion.ProvinciaDTO;
import com.cramirez.backendcramirez.entity.localizacion.Provincia;
import com.cramirez.backendcramirez.repository.localizacion.ProvinciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    public List<ProvinciaDTO> findAll() {
        return provinciaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ProvinciaDTO> findById(Integer id) {
        return provinciaRepository.findById(id).map(this::convertToDTO);
    }

    public ProvinciaDTO save(ProvinciaDTO provinciaDTO) {
        Provincia provincia = convertToEntity(provinciaDTO);
        Provincia savedProvincia = provinciaRepository.save(provincia);
        return convertToDTO(savedProvincia);
    }

    public void deleteById(Integer id) {
        provinciaRepository.deleteById(id);
    }

    private ProvinciaDTO convertToDTO(Provincia provincia) {
        return new ProvinciaDTO(
                provincia.getIdProvincia(),
                provincia.getIdDepartamento(), // Ya que en la entidad Provincia es un int
                provincia.getNombreProvincia()
        );
    }

    private Provincia convertToEntity(ProvinciaDTO dto) {
        return new Provincia(
                dto.getIdProvincia(),
                dto.getNombreProvincia(),
                dto.getIdDepartamento()
        );
    }
}