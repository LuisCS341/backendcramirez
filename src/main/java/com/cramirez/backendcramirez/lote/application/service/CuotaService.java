package com.cramirez.backendcramirez.lote.application.service;

import com.cramirez.backendcramirez.lote.domain.entity.Cuota;
import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import com.cramirez.backendcramirez.lote.dto.CuotaDTO;
import com.cramirez.backendcramirez.lote.dto.LinderoDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.CuotaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    public List<CuotaDTO> getAllCuota() {
        List<Cuota> cuota = cuotaRepository.findAll();
        return cuota.stream().map(this::convertirACuotaDTO).collect(Collectors.toList());
    }

    public Cuota convertToCuotaEntity(CuotaDTO cuotaDTO) {
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
        cuota.setCantidadCuotas(cuotaDTO.getCantidadCuotas());
        cuota.setCantidadCuotaLetras(cuotaDTO.getCantidadCuotaLetras());
        cuota.setCantidadCuotaCuentaRecaudadora(cuotaDTO.getCantidadCuotaCuentaRecaudadora());
        cuota.setCantidadCuotaBanco(cuotaDTO.getCantidadCuotaBanco());
        cuota.setCuotaPendientePago(cuotaDTO.getCuotaPendientePago());
        cuota.setSaldoLote(cuotaDTO.getSaldoLote());
        cuota.setSaldoLoteLetras(cuotaDTO.getSaldoLoteLetras());

        return cuotaRepository.save(cuota);
    }

    public CuotaDTO convertirACuotaDTO(Cuota cuota) {
        CuotaDTO dto = new CuotaDTO();
        dto.setIdLote(cuota.getIdLote());
        dto.setIdCuota(cuota.getIdCuota());
        dto.setLetrasPendientePago(cuota.getLetrasPendientePago());
        dto.setCuentaRecaudadora(cuota.getCuentaRecaudadora());
        dto.setCuotaInicialIncluyeSeparacion(cuota.getCuotaInicialIncluyeSeparacion());
        dto.setCuotaInicialIncluyeSeparacionLetras(cuota.getCuotaInicialIncluyeSeparacionLetras());
        dto.setMontoCuotas(cuota.getMontoCuotas());
        dto.setMontoCuotaLetras(cuota.getMontoCuotaLetras());
        dto.setFechaPago(cuota.getFechaPago());
        dto.setCuotaInicialBanco(cuota.getCuotaInicialBanco());
        dto.setCantidadCuotas(cuota.getCantidadCuotas());
        dto.setCantidadCuotaLetras(cuota.getCantidadCuotaLetras());
        dto.setCantidadCuotaCuentaRecaudadora(cuota.getCantidadCuotaCuentaRecaudadora());
        dto.setCantidadCuotaBanco(cuota.getCantidadCuotaBanco());
        dto.setCuotaPendientePago(cuota.getCuotaPendientePago());
        dto.setSaldoLote(cuota.getSaldoLote());
        dto.setSaldoLoteLetras(cuota.getSaldoLoteLetras());
        return dto;
    }
}
