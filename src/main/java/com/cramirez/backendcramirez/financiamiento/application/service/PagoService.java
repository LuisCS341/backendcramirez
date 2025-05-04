package com.cramirez.backendcramirez.financiamiento.application.service;

import com.cramirez.backendcramirez.dto.financiamiento.PagoDTO;
import com.cramirez.backendcramirez.entity.financiamiento.Pago;
import com.cramirez.backendcramirez.repository.financiamiento.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<PagoDTO> findAll() {
        return pagoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PagoDTO> findById(Long id) {
        return pagoRepository.findById(id).map(this::convertToDTO);
    }

    public PagoDTO save(PagoDTO pagoDTO) {
        Pago pago = convertToEntity(pagoDTO);
        Pago savedPago = pagoRepository.save(pago);
        return convertToDTO(savedPago);
    }

    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }

    private PagoDTO convertToDTO(Pago pago) {
        return new PagoDTO(
                pago.getId(),
                pago.getTipoId(),
                pago.getContratoId(),
                pago.getMonedaId(),
                pago.getMonto(),
                pago.getCantidadCuotas(),
                pago.getMontoCuotas(),
                pago.getCuotasExtraordinarias(),
                pago.getMantenimientoMensual()
        );
    }

    private Pago convertToEntity(PagoDTO dto) {
        return new Pago(
                dto.getId(),
                dto.getTipoId(),
                dto.getContratoId(),
                dto.getMonedaId(),
                dto.getMonto(),
                dto.getCantidadCuotas(),
                dto.getMontoCuotas(),
                dto.getCuotasExtraordinarias(),
                dto.getMantenimientoMensual()
        );
    }
}
