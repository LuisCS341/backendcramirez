package com.cramirez.backendcramirez.lote.application.service;

import com.cramirez.backendcramirez.lote.domain.entity.Cuota;
import com.cramirez.backendcramirez.lote.dto.CuotaDTO;

import com.cramirez.backendcramirez.lote.infrastructure.repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    public Cuota convertToEntity(CuotaDTO cuotaDTO) {
        Cuota cuota = new Cuota();
        cuota.setIdLote(cuotaDTO.getIdLote());
        cuota.setIdCuota(cuotaDTO.getIdCuota());
        cuota.setLetrasPendientePago(cuotaDTO.getLetrasPendientePago());
        cuota.setCuentaRecaudadora(cuotaDTO.getCuentaRecaudadora());
        cuota.setCuotaInicialIncluyeSeparacion(cuotaDTO.getCuotaInicialIncluyeSeparacion());
        cuota.setCuotaInicialIncluyeSeparacionLetras(cuotaDTO.getCuotaInicialIncluyeSeparacionLetras());
        cuota.setMontoCuotas(cuotaDTO.getMontoCuotas());
        cuota.setMontoCuotaLetras(cuotaDTO.getMontoCuotaLetras());
        cuota.setFechaPago(cuotaDTO.getFechaPago());
        cuota.setCuotaInicialBanco(cuotaDTO.getCuotaInicialBanco());
        cuota.setSaldoLote(cuotaDTO.getSaldoLote());
        cuota.setSaldoLoteLetras(cuotaDTO.getSaldoLoteLetras());
        cuota.setCantidadCuotas(cuotaDTO.getCantidadCuotas());
        cuota.setCantidadCuotaLetras(cuotaDTO.getCantidadCuotaLetras());
        cuota.setCantidadCuotaCuentaRecaudadora(cuotaDTO.getCantidadCuotaCuentaRecaudadora());
        cuota.setCantidadCuotaBanco(cuotaDTO.getCantidadCuotaBanco());
        cuota.setCuotaPendientePago(cuotaDTO.getCuotaPendientePago());

        return cuotaRepository.save(cuota);
    }

    public List<CuotaDTO> convertirACuotaDTO() {
        return cuotaRepository.findAll().stream().map(cuota -> {
            CuotaDTO dto = new CuotaDTO();
            dto.setIdLote(cuota.getIdLote());
            dto.setIdCuota(cuota.getIdCuota());
            dto.setLetrasPendientePago(cuota.getLetrasPendientePago());
            dto.setCuentaRecaudadora(cuota.getCuentaRecaudadora());
            dto.setCuotaInicialIncluyeSeparacionLetras(cuota.getCuotaInicialIncluyeSeparacionLetras());
            dto.setCuotaInicialIncluyeSeparacion(cuota.getCuotaInicialIncluyeSeparacion());
            dto.setMontoCuotas(cuota.getMontoCuotas());
            dto.setMontoCuotaLetras(cuota.getMontoCuotaLetras());
            dto.setFechaPago(cuota.getFechaPago());
            dto.setCuotaInicialBanco(cuota.getCuotaInicialBanco());
            dto.setSaldoLote(cuota.getSaldoLote());
            dto.setSaldoLoteLetras(cuota.getSaldoLoteLetras());
            dto.setCantidadCuotas(cuota.getCantidadCuotas());
            dto.setCantidadCuotaLetras(cuota.getCantidadCuotaLetras());
            dto.setCantidadCuotaCuentaRecaudadora(cuota.getCantidadCuotaCuentaRecaudadora());
            dto.setCantidadCuotaBanco(cuota.getCantidadCuotaBanco());
            dto.setCuotaPendientePago(cuota.getCuotaPendientePago());
            return dto;
        }).collect(Collectors.toList());
    }
}
