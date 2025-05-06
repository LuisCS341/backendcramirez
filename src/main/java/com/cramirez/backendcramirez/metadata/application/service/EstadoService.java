package com.cramirez.backendcramirez.metadata.application.service;
import com.cramirez.backendcramirez.metadata.domain.entity.Estado;
import com.cramirez.backendcramirez.metadata.dto.EstadoDTO;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<EstadoDTO> getAllEstados() {
        List<Estado> estados = estadoRepository.findAll();
        return estados.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<EstadoDTO> getEstadoById(Integer id) {
        return estadoRepository.findById(id).map(this::convertToDTO);
    }

    public EstadoDTO saveEstado(EstadoDTO estadoDTO) {
        Estado estado = convertToEntity(estadoDTO);
        Estado savedEstado = estadoRepository.save(estado);
        return convertToDTO(savedEstado);
    }

    public void deleteEstado(Integer id) {
        estadoRepository.deleteById(id);
    }

    private EstadoDTO convertToDTO(Estado estado) {
        EstadoDTO dto = new EstadoDTO();
        dto.setIdEstado(estado.getIdEstado());
        dto.setEstado(estado.getEstado());
        return dto;
    }

    private Estado convertToEntity(EstadoDTO dto) {
        Estado estado = new Estado();
        estado.setIdEstado(dto.getIdEstado());
        estado.setEstado(dto.getEstado());
        return estado;
    }
}
