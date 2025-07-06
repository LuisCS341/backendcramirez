package com.cramirez.backendcramirez.lote.application.service;

import com.cramirez.backendcramirez.lote.domain.entity.Matriz;
import com.cramirez.backendcramirez.lote.dto.MatrizDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.MatrizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatrizService {

    private final MatrizRepository matrizRepository;

    @Autowired
    public MatrizService(MatrizRepository matrizRepository) {
        this.matrizRepository = matrizRepository;
    }

    public Optional<MatrizDTO> getMatrizById(Integer id) {
        return matrizRepository.findById(id).map(this::convertToMatrizDTO);
    }

    public Optional<MatrizDTO> getMatrizByIdLote(Integer idLote) {
        return matrizRepository.findByIdLote(idLote).map(this::convertToMatrizDTO);
    }

    public MatrizDTO saveMatriz(MatrizDTO matrizDTO) {
        Matriz matriz = convertToMatrizEntity(matrizDTO);
        Matriz savedMatriz = matrizRepository.save(matriz);
        return convertToMatrizDTO(savedMatriz);
    }

    public void deleteMatriz(Integer id) {
        matrizRepository.deleteById(id);
    }

    private MatrizDTO convertToMatrizDTO(Matriz matriz) {
        MatrizDTO dto = new MatrizDTO();
        dto.setIdMatriz(matriz.getIdMatriz());
        dto.setIdLote(matriz.getIdLote());
        dto.setIdDepartamentoMatriz(matriz.getIdDepartamentoMatriz());
        dto.setIdProvinciaMatriz(matriz.getIdProvinciaMatriz());
        dto.setIdDistritoMatriz(matriz.getIdDistritoMatriz());
        dto.setUbicacionMatriz(matriz.getUbicacionMatriz());
        dto.setAreaMatrizHasMatriz(matriz.getAreaMatrizHasMatriz());
        dto.setRegistroMatriz(matriz.getRegistroMatriz());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastralMatriz(matriz.getUnidadCatastralMatriz());
        dto.setUrbanizacionMatriz(matriz.getUrbanizacionMatriz());
        dto.setCompraventaMatriz(matriz.getCompraventaMatriz());
        dto.setSituacionLegalMatriz(matriz.getSituacionLegalMatriz());
        return dto;
    }

    private Matriz convertToMatrizEntity(MatrizDTO dto) {
        Matriz matriz = new Matriz();
        matriz.setIdMatriz(dto.getIdMatriz());
        matriz.setIdLote(dto.getIdLote());
        matriz.setIdDepartamentoMatriz(dto.getIdDepartamentoMatriz());
        matriz.setIdProvinciaMatriz(dto.getIdProvinciaMatriz());
        matriz.setIdDistritoMatriz(dto.getIdDistritoMatriz());
        matriz.setUbicacionMatriz(dto.getUbicacionMatriz());
        matriz.setAreaMatrizHasMatriz(dto.getAreaMatrizHasMatriz());
        matriz.setRegistroMatriz(dto.getRegistroMatriz());
        matriz.setPartidaMatriz(dto.getPartidaMatriz());
        matriz.setUnidadCatastralMatriz(dto.getUnidadCatastralMatriz());
        matriz.setUrbanizacionMatriz(dto.getUrbanizacionMatriz());
        matriz.setCompraventaMatriz(dto.getCompraventaMatriz());
        matriz.setSituacionLegalMatriz(dto.getSituacionLegalMatriz());
        return matriz;
    }
}
