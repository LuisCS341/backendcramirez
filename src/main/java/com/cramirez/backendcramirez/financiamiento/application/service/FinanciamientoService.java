package com.cramirez.backendcramirez.financiamiento.application.service;
import com.cramirez.backendcramirez.financiamiento.domain.entity.Financiamiento;
import com.cramirez.backendcramirez.financiamiento.dto.FinanciamientoDTO;
import com.cramirez.backendcramirez.financiamiento.infrastructure.repository.FinanciamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FinanciamientoService {

    @Autowired
    private FinanciamientoRepository financiamientoRepository;

    // Obtener todos los financiamientos en formato DTO
    public List<FinanciamientoDTO> getAllFinanciamientos() {
        List<Financiamiento> financiamientos = financiamientoRepository.findAll();
        return financiamientos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener financiamiento por ID en formato DTO
    public Optional<FinanciamientoDTO> getFinanciamientoById(Integer id) {
        return financiamientoRepository.findById(id).map(this::convertToDTO);
    }

    // Guardar financiamiento desde un DTO
    public FinanciamientoDTO saveFinanciamiento(FinanciamientoDTO financiamientoDTO) {
        Financiamiento financiamiento = convertToEntity(financiamientoDTO);
        Financiamiento savedEntity = financiamientoRepository.save(financiamiento);
        return convertToDTO(savedEntity);
    }

    // Eliminar financiamiento por ID
    public void deleteFinanciamiento(Integer id) {
        financiamientoRepository.deleteById(id);
    }

    // Métodos de conversión entre DTO y Entidad
    private FinanciamientoDTO convertToDTO(Financiamiento financiamiento) {
        FinanciamientoDTO dto = new FinanciamientoDTO();
        dto.setIdFinanciamiento(financiamiento.getIdFinanciamiento());
        dto.setIdContrato(financiamiento.getIdContrato());
        dto.setSeparacion(financiamiento.getSeparacion());
        dto.setCantidadCuotas(financiamiento.getCantidadCuotas());
        dto.setMontoCuota(financiamiento.getMontoCuota());
        dto.setCuotasPendientes(financiamiento.getCuotasPendientes());
        dto.setDiaPago(financiamiento.getDiaPago());

        return dto;
    }

    private Financiamiento convertToEntity(FinanciamientoDTO dto) {
        Financiamiento financiamiento = new Financiamiento();
        financiamiento.setIdFinanciamiento(dto.getIdFinanciamiento());
        financiamiento.setIdContrato(dto.getIdContrato());
        financiamiento.setSeparacion(dto.getSeparacion());
        financiamiento.setCantidadCuotas(dto.getCantidadCuotas());
        financiamiento.setMontoCuota(dto.getMontoCuota());
        financiamiento.setCuotasPendientes(dto.getCuotasPendientes());
        financiamiento.setDiaPago(dto.getDiaPago());
        return financiamiento;
    }
}
