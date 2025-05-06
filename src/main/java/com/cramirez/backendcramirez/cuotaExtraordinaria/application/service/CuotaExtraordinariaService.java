package com.cramirez.backendcramirez.cuotaExtraordinaria.application.service;
import com.cramirez.backendcramirez.cuotaExtraordinaria.domain.entity.CuotaExtraordinaria;
import com.cramirez.backendcramirez.cuotaExtraordinaria.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.cuotaExtraordinaria.infrastructure.repository.CuotaExtraordinariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuotaExtraordinariaService {

    @Autowired
    private CuotaExtraordinariaRepository repository;

    public List<CuotaExtraordinariaDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CuotaExtraordinariaDTO getById(int id) {
        Optional<CuotaExtraordinaria> optional = repository.findById(id);
        return optional.map(this::convertToDTO).orElse(null);
    }

    public CuotaExtraordinariaDTO save(CuotaExtraordinariaDTO dto) {
        CuotaExtraordinaria entity = convertToEntity(dto);
        CuotaExtraordinaria saved = repository.save(entity);
        return convertToDTO(saved);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // DTO → Entity
    private CuotaExtraordinaria convertToEntity(CuotaExtraordinariaDTO dto) {
        CuotaExtraordinaria entity = new CuotaExtraordinaria();
        entity.setIdCuotaExtraordinaria(dto.getIdCuotaExtraordinaria());
        entity.setIdLote(dto.getIdLote());
        entity.setCuotaExtraordinaria(dto.getCuotaExtraordinaria());
        entity.setMantenimientoMensual(dto.getMantenimientoMensual());
        entity.setMantenimientoMensualLetras(dto.getMantenimientoMensualLetras());
        entity.setEstadoCuenta(dto.getEstadoCuenta());
        entity.setMontoDeudaLetra(dto.getMontoDeudaLetra());
        entity.setCuotaPendientePago(dto.getCuotaPendientePago());
        return entity;
    }

    // Entity → DTO
    private CuotaExtraordinariaDTO convertToDTO(CuotaExtraordinaria entity) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(entity.getIdCuotaExtraordinaria());
        dto.setIdLote(entity.getIdLote());
        dto.setCuotaExtraordinaria(entity.getCuotaExtraordinaria());
        dto.setMantenimientoMensual(entity.getMantenimientoMensual());
        dto.setMantenimientoMensualLetras(entity.getMantenimientoMensualLetras());
        dto.setEstadoCuenta(entity.getEstadoCuenta());
        dto.setMontoDeudaLetra(entity.getMontoDeudaLetra());
        dto.setCuotaPendientePago(entity.getCuotaPendientePago());
        return dto;
    }
}
