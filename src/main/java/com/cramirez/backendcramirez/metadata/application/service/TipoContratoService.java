package com.cramirez.backendcramirez.metadata.application.service;

import com.cramirez.backendcramirez.dto.metadata.TipoContratoDTO;
import com.cramirez.backendcramirez.entity.metadata.TipoContrato;
import com.cramirez.backendcramirez.repository.metadata.TipoContratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoContratoService {
    private final TipoContratoRepository tipoContratoRepository;

    public TipoContratoService(TipoContratoRepository tipoContratoRepository) {
        this.tipoContratoRepository = tipoContratoRepository;
    }

    public List<TipoContratoDTO> findAll() {
        return tipoContratoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoContratoDTO> findById(int id) {
        return tipoContratoRepository.findById(id).map(this::convertToDTO);
    }

    public TipoContratoDTO save(TipoContratoDTO tipoContratoDTO) {
        TipoContrato tipoContrato = convertToEntity(tipoContratoDTO);
        TipoContrato savedTipoContrato = tipoContratoRepository.save(tipoContrato);
        return convertToDTO(savedTipoContrato);
    }

    public void deleteById(int id) {
        tipoContratoRepository.deleteById(id);
    }

    private TipoContratoDTO convertToDTO(TipoContrato tipoContrato) {
        return new TipoContratoDTO(
                tipoContrato.getIdTipoContrato(),
                tipoContrato.getTipoContrato()
        );
    }

    private TipoContrato convertToEntity(TipoContratoDTO dto) {
        return new TipoContrato(
                dto.getIdTipoContrato(),
                dto.getTipoContrato()
        );
    }
}