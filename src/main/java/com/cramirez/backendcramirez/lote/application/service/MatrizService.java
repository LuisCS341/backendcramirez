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
        dto.setIdDepartamento(matriz.getIdDepartamento());
        dto.setIdProvincia(matriz.getIdProvincia());
        dto.setIdDistrito(matriz.getIdDistrito());
        dto.setUbicacion(matriz.getUbicacion());
        dto.setAreaMatrizHas(matriz.getAreaMatrizHas());
        dto.setRegistro(matriz.getRegistro());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastral(matriz.getUnidadCatastral());
        dto.setUrbanizacion(matriz.getUrbanizacion());
        dto.setCompraventa(matriz.getCompraventa());
        dto.setSituacionLegal(matriz.getSituacionLegal());
        return dto;
    }

    private Matriz convertToMatrizEntity(MatrizDTO dto) {
        Matriz matriz = new Matriz();
        matriz.setIdMatriz(dto.getIdMatriz());
        matriz.setIdLote(dto.getIdLote());
        matriz.setIdDepartamento(dto.getIdDepartamento());
        matriz.setIdProvincia(dto.getIdProvincia());
        matriz.setIdDistrito(dto.getIdDistrito());
        matriz.setUbicacion(dto.getUbicacion());
        matriz.setAreaMatrizHas(dto.getAreaMatrizHas());
        matriz.setRegistro(dto.getRegistro());
        matriz.setPartidaMatriz(dto.getPartidaMatriz());
        matriz.setUnidadCatastral(dto.getUnidadCatastral());
        matriz.setUrbanizacion(dto.getUrbanizacion());
        matriz.setCompraventa(dto.getCompraventa());
        matriz.setSituacionLegal(dto.getSituacionLegal());
        return matriz;
    }
}
