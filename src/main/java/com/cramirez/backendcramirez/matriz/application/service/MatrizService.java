package com.cramirez.backendcramirez.matriz.application.service;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DepartamentoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ProvinciaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import com.cramirez.backendcramirez.matriz.domain.entity.Matriz;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import com.cramirez.backendcramirez.matriz.infrastructure.repository.MatrizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatrizService {

    private final MatrizRepository matrizRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;
    private final UbicacionRepository ubicacionRepository;

    @Autowired
    public MatrizService(MatrizRepository matrizRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, UbicacionRepository ubicacionRepository) {
        this.matrizRepository = matrizRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<MatrizDTO> getAllMatrices() {
        List<Matriz> matrices = matrizRepository.findAll();
        return matrices.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<MatrizDTO> getMatrizById(Integer id) {
        return matrizRepository.findById(id).map(this::convertToDTO);
    }

    public MatrizDTO saveMatriz(MatrizDTO matrizDTO) {
        Matriz matriz = convertToEntity(matrizDTO);
        Matriz savedMatriz = matrizRepository.save(matriz);
        return convertToDTO(savedMatriz);
    }

    public void deleteMatriz(Integer id) {
        matrizRepository.deleteById(id);
    }

    private MatrizDTO convertToDTO(Matriz matriz) {
        MatrizDTO dto = new MatrizDTO();
        dto.setIdMatriz(matriz.getIdMatriz());
        dto.setIdLote(matriz.getIdLote());
        dto.setIdDistrito(matriz.getIdDistrito());
        dto.setIdProvincia(matriz.getIdProvincia());
        dto.setIdDepartamento(matriz.getIdDepartamento());
        dto.setIdUbicacion(matriz.getIdUbicacion());
        dto.setAreaMatrizHas(matriz.getAreaMatrizHas());
        dto.setRegistrosDE(matriz.getRegistrosDE());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastral(matriz.getUnidadCatastral());
        dto.setUrbanizacionMatriz(matriz.getUrbanizacionMatriz());
        dto.setCompraventaMatriz(matriz.getCompraventaMatriz());
        dto.setSituacionLegal(matriz.getSituacionLegal());
        dto.setAlicuota(matriz.getAlicuota());
        dto.setAlicuotaLetras(matriz.getAlicuotaLetras());

        dto.setDepartamento(obtenerTexto(departamentoRepository.findById(matriz.getIdDepartamento()), "NombreDepartamento"));
        dto.setProvincia(obtenerTexto(provinciaRepository.findById(matriz.getIdProvincia()), "NombreProvincia"));
        dto.setDistrito(obtenerTexto(distritoRepository.findById(matriz.getIdDistrito()), "NombreDistrito"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(matriz.getIdUbicacion()), "Ubicacion"));

        return dto;
    }

    private Matriz convertToEntity(MatrizDTO dto) {
        Matriz matriz = new Matriz();
        matriz.setIdLote(dto.getIdLote());
        matriz.setIdDistrito(dto.getIdDistrito());
        matriz.setIdProvincia(dto.getIdProvincia());
        matriz.setIdDepartamento(dto.getIdDepartamento());
        matriz.setIdUbicacion(dto.getIdUbicacion());
        matriz.setAreaMatrizHas(dto.getAreaMatrizHas());
        matriz.setRegistrosDE(dto.getRegistrosDE());
        matriz.setPartidaMatriz(dto.getPartidaMatriz());
        matriz.setUnidadCatastral(dto.getUnidadCatastral());
        matriz.setUrbanizacionMatriz(dto.getUrbanizacionMatriz());
        matriz.setCompraventaMatriz(dto.getCompraventaMatriz());
        matriz.setSituacionLegal(dto.getSituacionLegal());
        matriz.setAlicuota(dto.getAlicuota());
        matriz.setAlicuotaLetras(dto.getAlicuotaLetras());
        return matriz;
    }

    private String obtenerTexto(Optional<?> entidad, String campo) {
        return entidad.map(e -> {
            try {
                return e.getClass().getMethod("get" + campo).invoke(e).toString();
            } catch (Exception ex) {
                return null;
            }
        }).orElse(null);
    }
}

