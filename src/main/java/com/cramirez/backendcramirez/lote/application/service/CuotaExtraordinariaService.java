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
        cuota.setMantenimientoMensual(cuotaDTO.getMantenimientoMensual());
        cuota.setMantenimientoMensualLetras(cuotaDTO.getMantenimientoMensualLetras());
        cuota.setEstadoCuenta(cuotaDTO.getEstadoCuenta());
        cuota.setMontoDeudaLetra(cuotaDTO.getMontoDeudaLetra());
        cuota.setCuotaPendientePago(cuotaDTO.getCuotaPendientePago());
        cuota.setLetrasPendientePago(cuotaDTO.getLetrasPendientePago());
        cuota.setFechaEntrega(cuotaDTO.getFechaEntrega());
        cuota.setCartaNoAdeudo(cuotaDTO.getCartaNoAdeudo());
        cuota.setCertificadoLote(cuotaDTO.getCertificadoLote());
        cuota.setMediosPago(cuotaDTO.getMediosPago());
        cuota.setPlano1(cuotaDTO.getPlano1());
        cuota.setPlano2(cuotaDTO.getPlano2());
        cuota.setEnvioMinuta(cuotaDTO.getEnvioMinuta());
        cuota.setFechaCita(cuotaDTO.getFechaCita());
        cuota.setHoraCita(cuotaDTO.getHoraCita());
        cuota.setModificarMinuta(cuotaDTO.getModificarMinuta());
        cuota.setMinutaEscaneada(cuotaDTO.getMinutaEscaneada());
        cuota.setExpNotaria(cuotaDTO.getExpNotaria());
        return cuota;
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
