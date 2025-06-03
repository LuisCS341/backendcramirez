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
                .map(this::convertirACuotaDTO)
                .collect(Collectors.toList());
    }

    public CuotaExtraordinariaDTO getById(int id) {
        Optional<CuotaExtraordinaria> optional = repository.findById(id);
        return optional.map(this::convertirACuotaDTO).orElse(null);
    }

    public CuotaExtraordinariaDTO save(CuotaExtraordinariaDTO dto) {
        CuotaExtraordinaria entity = convertToEntity(dto);
        CuotaExtraordinaria saved = repository.save(entity);
        return convertirACuotaDTO(saved);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }


    private CuotaExtraordinaria convertToEntity(CuotaExtraordinariaDTO dto) {
        CuotaExtraordinaria entity = new CuotaExtraordinaria();
        entity.setIdCuotaExtraordinaria(dto.getIdCuotaExtraordinaria());
        entity.setIdLote(dto.getIdLote());
        entity.setCantidadCuotaExtraordinaria(dto.getCantidadCuotaExtraordinaria());
        entity.setMontoCuotaExtraordinaria(dto.getMontoCuotaExtraordinaria());
        entity.setDiaPagoNumero(dto.getDiaPagoNumero());
        entity.setDiaPagoLetras(dto.getDiaPagoLetras());
        entity.setPagoInicial(dto.getPagoInicial());
        entity.setSeparacion(dto.getSeparacion());
        entity.setEstadoCuenta(dto.getEstadoCuenta());
        entity.setMontoDeudaLetra(dto.getMontoDeudaLetra());
        entity.setCuotaPendientePago(dto.getCuotaPendientePago());
        entity.setPonerMonto(dto.getPonerMonto());
        return entity;
    }

    private CuotaExtraordinariaDTO convertirACuotaDTO(CuotaExtraordinaria entity) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(entity.getIdCuotaExtraordinaria());
        dto.setIdLote(entity.getIdLote());
        dto.setCantidadCuotaExtraordinaria(entity.getCantidadCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinaria(entity.getMontoCuotaExtraordinaria());
        dto.setDiaPagoNumero(entity.getDiaPagoNumero());
        dto.setDiaPagoLetras(entity.getDiaPagoLetras());
        dto.setPagoInicial(entity.getPagoInicial());
        dto.setSeparacion(entity.getSeparacion());
        dto.setEstadoCuenta(entity.getEstadoCuenta());
        dto.setMontoDeudaLetra(entity.getMontoDeudaLetra());
        dto.setCuotaPendientePago(entity.getCuotaPendientePago());
        dto.setPonerMonto(entity.getPonerMonto());
    return dto;
    }
}
