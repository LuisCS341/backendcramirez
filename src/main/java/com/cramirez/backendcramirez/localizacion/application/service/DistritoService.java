package com.cramirez.backendcramirez.localizacion.application.service;
import com.cramirez.backendcramirez.localizacion.domain.entity.Distrito;
import com.cramirez.backendcramirez.localizacion.domain.entity.Provincia;
import com.cramirez.backendcramirez.localizacion.dto.DistritoDTO;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistritoService {

    private final DistritoRepository distritoRepository;

    public DistritoService(DistritoRepository distritoRepository) {
        this.distritoRepository = distritoRepository;
    }

    public List<DistritoDTO> findAll() {
        return distritoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DistritoDTO> findById(Integer id) {
        return distritoRepository.findById(id).map(this::convertToDTO);
    }

    public DistritoDTO save(DistritoDTO distritoDTO) {
        Distrito distrito = convertToEntity(distritoDTO);
        Distrito savedDistrito = distritoRepository.save(distrito);
        return convertToDTO(savedDistrito);
    }

    public void deleteById(Integer id) {
        distritoRepository.deleteById(id);
    }

    private DistritoDTO convertToDTO(Distrito distrito) {
        return new DistritoDTO(
                distrito.getIdDistrito(),
                distrito.getNombreDistrito(),
                distrito.getIdProvincia()
        );
    }

    private Distrito convertToEntity(DistritoDTO dto) {
        Provincia provincia = new Provincia();
        provincia.setIdProvincia(dto.getIdProvincia()); // Solo se asigna el ID sin buscar en la BD

        return new Distrito(
                dto.getIdDistrito(),
                dto.getNombreDistrito(),
                dto.getIdProvincia()
        );
    }
}
