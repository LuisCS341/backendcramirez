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
        entity.setMantenimientoMensual(dto.getMantenimientoMensual());
        entity.setMantenimientoMensualLetras(dto.getMantenimientoMensualLetras());
        entity.setEstadoCuenta(dto.getEstadoCuenta());
        entity.setMontoDeudaLetra(dto.getMontoDeudaLetra());
        entity.setCuotaPendientePago(dto.getCuotaPendientePago());
        entity.setLetrasPendientePago(dto.getLetrasPendientePago());
        entity.setFechaEntrega(dto.getFechaEntrega());
        entity.setCartaNoAdeudo(dto.getCartaNoAdeudo());
        entity.setCertificadoLote(dto.getCertificadoLote());
        entity.setMediosPago(dto.getMediosPago());
        entity.setPlano1(dto.getPlano1());
        entity.setPlano2(dto.getPlano2());
        entity.setEnvioMinuta(dto.getEnvioMinuta());
        entity.setFechaCita(dto.getFechaCita());
        entity.setHoraCita(dto.getHoraCita());
        entity.setModificarMinuta(dto.getModificarMinuta());
        entity.setMinutaEscaneada(dto.getMinutaEscaneada());
        entity.setExpNotaria(dto.getExpNotaria());
        return entity;
    }

    private CuotaExtraordinariaDTO convertirACuotaDTO(CuotaExtraordinaria entity) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(entity.getIdCuotaExtraordinaria());
        dto.setIdLote(entity.getIdLote());
        dto.setCantidadCuotaExtraordinaria(entity.getCantidadCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinaria(entity.getMontoCuotaExtraordinaria());
        dto.setMantenimientoMensual(entity.getMantenimientoMensual());
        dto.setMantenimientoMensualLetras(entity.getMantenimientoMensualLetras());
        dto.setEstadoCuenta(entity.getEstadoCuenta());
        dto.setMontoDeudaLetra(entity.getMontoDeudaLetra());
        dto.setCuotaPendientePago(entity.getCuotaPendientePago());
        dto.setLetrasPendientePago(entity.getLetrasPendientePago());
        dto.setFechaEntrega(entity.getFechaEntrega());
        dto.setCartaNoAdeudo(entity.getCartaNoAdeudo());
        dto.setCertificadoLote(entity.getCertificadoLote());
        dto.setMediosPago(entity.getMediosPago());
        dto.setPlano1(entity.getPlano1());
        dto.setPlano2(entity.getPlano2());
        dto.setEnvioMinuta(entity.getEnvioMinuta());
        dto.setFechaCita(entity.getFechaCita());
        dto.setHoraCita(entity.getHoraCita());
        dto.setModificarMinuta(entity.getModificarMinuta());
        dto.setMinutaEscaneada(entity.getMinutaEscaneada());
        dto.setExpNotaria(entity.getExpNotaria());
        return dto;
    }
}
