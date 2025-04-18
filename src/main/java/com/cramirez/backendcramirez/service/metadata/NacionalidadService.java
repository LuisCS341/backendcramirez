package com.cramirez.backendcramirez.service.metadata;

import com.cramirez.backendcramirez.dto.metadata.NacionalidadDTO;
import com.cramirez.backendcramirez.entity.metadata.Nacionalidad;
import com.cramirez.backendcramirez.repository.metadata.NacionalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NacionalidadService {
    private final NacionalidadRepository nacionalidadRepository;

    public NacionalidadService(NacionalidadRepository nacionalidadRepository) {
        this.nacionalidadRepository = nacionalidadRepository;
    }

    public List<NacionalidadDTO> findAll() {
        return nacionalidadRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<NacionalidadDTO> findById(int id) { // Cambiado de Long a int
        return nacionalidadRepository.findById(id).map(this::convertToDTO);
    }

    public NacionalidadDTO save(NacionalidadDTO nacionalidadDTO) {
        Nacionalidad nacionalidad = convertToEntity(nacionalidadDTO);
        Nacionalidad savedNacionalidad = nacionalidadRepository.save(nacionalidad);
        return convertToDTO(savedNacionalidad);
    }

    public void deleteById(int id) { // Cambiado de Long a int
        nacionalidadRepository.deleteById(id);
    }

    private NacionalidadDTO convertToDTO(Nacionalidad nacionalidad) {
        return new NacionalidadDTO(nacionalidad.getIdNacionalidad(), nacionalidad.getNombreNacionalidad());
    }

    private Nacionalidad convertToEntity(NacionalidadDTO dto) {
        return new Nacionalidad(dto.getIdNacionalidad(), dto.getNombreNacionalidad());
    }
}
