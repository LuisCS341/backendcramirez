package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.lote.domain.entity.CuotaExtraordinaria;
import com.cramirez.backendcramirez.lote.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.CuotaExtraordinariaRepository;
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


    private CuotaExtraordinaria convertToEntity(CuotaExtraordinariaDTO cuotaDTO) {
        CuotaExtraordinaria cuota = new CuotaExtraordinaria();
        cuota.setIdCuotaExtraordinaria(cuotaDTO.getIdCuotaExtraordinaria());
        cuota.setIdLote(cuotaDTO.getIdLote());
        cuota.setCantidadCuotaExtraordinaria(cuotaDTO.getCantidadCuotaExtraordinaria());
        cuota.setMontoCuotaExtraordinaria(cuotaDTO.getMontoCuotaExtraordinaria());
        cuota.setMediosPago(cuotaDTO.getMediosPago());
        return cuota;
    }

    private CuotaExtraordinariaDTO convertirACuotaDTO(CuotaExtraordinaria entity) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(entity.getIdCuotaExtraordinaria());
        dto.setIdLote(entity.getIdLote());
        dto.setCantidadCuotaExtraordinaria(entity.getCantidadCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinaria(entity.getMontoCuotaExtraordinaria());
        dto.setMediosPago(entity.getMediosPago());
        return dto;
    }
}
