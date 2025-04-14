package com.cramirez.backendcramirez.service.metadata;

import com.cramirez.backendcramirez.dto.metadata.TipoEntidadDTO;
import com.cramirez.backendcramirez.entity.metadata.TipoEntidad;
import com.cramirez.backendcramirez.repository.metadata.TipoEntidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoEntidadService {
    private final TipoEntidadRepository tipoEntidadRepository;

    public TipoEntidadService(TipoEntidadRepository tipoEntidadRepository) {
        this.tipoEntidadRepository = tipoEntidadRepository;
    }

    public List<TipoEntidadDTO> findAll() {
        return tipoEntidadRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoEntidadDTO> findById(Long id) {
        return tipoEntidadRepository.findById(id).map(this::convertToDTO);
    }

    public TipoEntidadDTO save(TipoEntidadDTO tipoEntidadDTO) {
        TipoEntidad tipoEntidad = convertToEntity(tipoEntidadDTO);
        TipoEntidad savedTipoEntidad = tipoEntidadRepository.save(tipoEntidad);
        return convertToDTO(savedTipoEntidad);
    }

    public void deleteById(Long id) {
        tipoEntidadRepository.deleteById(id);
    }

    private TipoEntidadDTO convertToDTO(TipoEntidad tipoEntidad) {
        return new TipoEntidadDTO(
                tipoEntidad.getId(),
                tipoEntidad.getTipoEntidad()
        );
    }

    private TipoEntidad convertToEntity(TipoEntidadDTO dto) {
        return new TipoEntidad(
                dto.getId(),
                dto.getTipoEntidad()
        );
    }
}
