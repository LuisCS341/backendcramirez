package com.cramirez.backendcramirez.localizacion.application.service;
import com.cramirez.backendcramirez.localizacion.domain.entity.Ubicacion;
import com.cramirez.backendcramirez.localizacion.dto.UbicacionDTO;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    // Obtener todas las ubicaciones en formato DTO
    public List<UbicacionDTO> getAllUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
        return ubicaciones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener ubicación por ID en formato DTO
    public Optional<UbicacionDTO> getUbicacionById(Integer id) {
        return ubicacionRepository.findById(id).map(this::convertToDTO);
    }

    // Guardar ubicación desde un DTO
    public UbicacionDTO saveUbicacion(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = convertToEntity(ubicacionDTO);
        Ubicacion savedEntity = ubicacionRepository.save(ubicacion);
        return convertToDTO(savedEntity);
    }

    // Eliminar ubicación por ID
    public void deleteUbicacion(Integer id) {
        ubicacionRepository.deleteById(id);
    }

    // Métodos de conversión entre DTO y Entidad
    private UbicacionDTO convertToDTO(Ubicacion ubicacion) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setIdUbicacion(ubicacion.getIdUbicacion());
        dto.setUbicacion(ubicacion.getUbicacion());
        return dto;
    }

    private Ubicacion convertToEntity(UbicacionDTO dto) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdUbicacion(dto.getIdUbicacion());
        ubicacion.setUbicacion(dto.getUbicacion());
        return ubicacion;
    }
}
