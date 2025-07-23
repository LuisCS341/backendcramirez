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
                .map(this::convertirACuotaExtraordinariaDTO)
                .collect(Collectors.toList());
    }

    public CuotaExtraordinariaDTO getById(int id) {
        Optional<CuotaExtraordinaria> optional = repository.findById(id);
        return optional.map(this::convertirACuotaExtraordinariaDTO).orElse(null);
    }

    public CuotaExtraordinariaDTO save(CuotaExtraordinariaDTO dto) {
        CuotaExtraordinaria entity = convertToEntity(dto);
        CuotaExtraordinaria saved = repository.save(entity);
        return convertirACuotaExtraordinariaDTO(saved);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }


    private CuotaExtraordinaria convertToEntity(CuotaExtraordinariaDTO cuotaExtraordinariaDTO) {
        CuotaExtraordinaria cuotaExtraordinaria = new CuotaExtraordinaria();
        cuotaExtraordinaria.setIdCuotaExtraordinaria(cuotaExtraordinariaDTO.getIdCuotaExtraordinaria());
        cuotaExtraordinaria.setIdLote(cuotaExtraordinariaDTO.getIdLote());
        cuotaExtraordinaria.setCantidadCuotaExtraordinaria(cuotaExtraordinariaDTO.getCantidadCuotaExtraordinaria());
        cuotaExtraordinaria.setMontoCuotaExtraordinaria(cuotaExtraordinariaDTO.getMontoCuotaExtraordinaria());
        return cuotaExtraordinaria;
    }

    private CuotaExtraordinariaDTO convertirACuotaExtraordinariaDTO(CuotaExtraordinaria cuotaExtraordinaria) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(cuotaExtraordinaria.getIdCuotaExtraordinaria());
        dto.setIdLote(cuotaExtraordinaria.getIdLote());
        dto.setCantidadCuotaExtraordinaria(cuotaExtraordinaria.getCantidadCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinaria(cuotaExtraordinaria.getMontoCuotaExtraordinaria());

        return dto;
    }
}
