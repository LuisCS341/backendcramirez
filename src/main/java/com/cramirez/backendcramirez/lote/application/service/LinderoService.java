package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import com.cramirez.backendcramirez.lote.dto.LinderoDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.LinderoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinderoService {

    private final LinderoRepository linderoRepository;

    @Autowired
    public LinderoService(LinderoRepository linderoRepository) {
        this.linderoRepository = linderoRepository;
    }

    public List<LinderoDTO> getAllLinderos() {
        List<Lindero> linderos = linderoRepository.findAll();
        return linderos.stream().map(this::convertToLinderoDTO).collect(Collectors.toList());
    }

    public Optional<LinderoDTO> getLinderoById(Integer id) {
        return linderoRepository.findById(id).map(this::convertToLinderoDTO);
    }

    public LinderoDTO saveLindero(LinderoDTO linderoDTO) {
        Lindero lindero = convertToEntity(linderoDTO);
        Lindero savedLindero = linderoRepository.save(lindero);
        return convertToLinderoDTO(savedLindero);
    }

    public void deleteLindero(Integer id) {
        linderoRepository.deleteById(id);
    }

    private LinderoDTO convertToLinderoDTO(Lindero lindero) {
        LinderoDTO dto = new LinderoDTO();
        dto.setIdLindero(lindero.getIdLindero());
        dto.setIdLote(lindero.getIdLote());
        dto.setPorLaDerecha(lindero.getPorLaDerecha());
        dto.setPorLaIzquierda(lindero.getPorLaIzquierda());
        dto.setPorElFrente(lindero.getPorElFrente());
        dto.setPorElFondo(lindero.getPorElFondo());
        return dto;
    }

    private Lindero convertToEntity(LinderoDTO dto) {
        Lindero lindero = new Lindero();
        lindero.setIdLote(dto.getIdLote());
        lindero.setPorLaDerecha(dto.getPorLaDerecha());
        lindero.setPorLaIzquierda(dto.getPorLaIzquierda());
        lindero.setPorElFrente(dto.getPorElFrente());
        lindero.setPorElFondo(dto.getPorElFondo());
        return lindero;
    }
}
