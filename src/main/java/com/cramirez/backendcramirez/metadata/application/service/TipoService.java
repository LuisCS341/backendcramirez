package com.cramirez.backendcramirez.metadata.application.service;

import com.cramirez.backendcramirez.dto.metadata.TipoDTO;
import com.cramirez.backendcramirez.entity.metadata.Tipo;
import com.cramirez.backendcramirez.repository.metadata.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoService {

    private final TipoRepository tipoRepository;

    @Autowired
    public TipoService(TipoRepository tipoRepository) {
        this.tipoRepository = tipoRepository;
    }

    public List<TipoDTO> getAllTipos() {
        List<Tipo> tipos = tipoRepository.findAll();
        return tipos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<TipoDTO> getTipoById(Integer id) {
        return tipoRepository.findById(id).map(this::convertToDTO);
    }

    public TipoDTO saveTipo(TipoDTO tipoDTO) {
        Tipo tipo = convertToEntity(tipoDTO);
        Tipo savedTipo = tipoRepository.save(tipo);
        return convertToDTO(savedTipo);
    }

    public void deleteTipo(Integer id) {
        tipoRepository.deleteById(id);
    }

    private TipoDTO convertToDTO(Tipo tipo) {
        TipoDTO dto = new TipoDTO();
        dto.setIdTipo(tipo.getIdTipo());
        dto.setTipo(tipo.getTipo());
        return dto;
    }

    private Tipo convertToEntity(TipoDTO dto) {
        Tipo tipo = new Tipo();
        tipo.setIdTipo(dto.getIdTipo());
        tipo.setTipo(dto.getTipo());
        return tipo;
    }
}
