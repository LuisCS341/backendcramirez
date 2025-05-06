package com.cramirez.backendcramirez.metadata.application.service;
import com.cramirez.backendcramirez.metadata.domain.entity.TipoPago;
import com.cramirez.backendcramirez.metadata.dto.TipoPagoDTO;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.TipoPagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoPagoService {
    private final TipoPagoRepository tipoPagoRepository;

    public TipoPagoService(TipoPagoRepository tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    public List<TipoPagoDTO> findAll() {
        return tipoPagoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoPagoDTO> findById(Long id) {
        return tipoPagoRepository.findById(id).map(this::convertToDTO);
    }

    public TipoPagoDTO save(TipoPagoDTO tipoPagoDTO) {
        TipoPago tipoPago = convertToEntity(tipoPagoDTO);
        TipoPago savedTipoPago = tipoPagoRepository.save(tipoPago);
        return convertToDTO(savedTipoPago);
    }

    public void deleteById(Long id) {
        tipoPagoRepository.deleteById(id);
    }

    private TipoPagoDTO convertToDTO(TipoPago tipoPago) {
        return new TipoPagoDTO(
                tipoPago.getId(),
                tipoPago.getTipoPago()
        );
    }

    private TipoPago convertToEntity(TipoPagoDTO dto) {
        return new TipoPago(
                dto.getId(),
                dto.getTipoPago()
        );
    }
}
