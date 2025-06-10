package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DepartamentoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ProvinciaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import com.cramirez.backendcramirez.lote.domain.entity.Matriz;
import com.cramirez.backendcramirez.lote.dto.MatrizDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.MatrizRepository;
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

    private Matriz convertToEntity(MatrizDTO matrizDTO) {
        Matriz matriz = new Matriz();
        matriz.setIdLote(matrizDTO.getIdLote());
        matriz.setIdDistrito(matrizDTO.getIdDistrito());
        matriz.setIdProvincia(matrizDTO.getIdProvincia());
        matriz.setIdDepartamento(matrizDTO.getIdDepartamento());
        matriz.setIdUbicacion(matrizDTO.getIdUbicacion());
        matriz.setAreaMatrizHas(matrizDTO.getAreaMatrizHas());
        matriz.setRegistrosDE(matrizDTO.getRegistrosDE());
        matriz.setPartidaMatriz(matrizDTO.getPartidaMatriz());
        matriz.setUnidadCatastral(matrizDTO.getUnidadCatastral());
        matriz.setUrbanizacionMatriz(matrizDTO.getUrbanizacionMatriz());
        matriz.setCompraventaMatriz(matrizDTO.getCompraventaMatriz());
        matriz.setSituacionLegal(matrizDTO.getSituacionLegal());
        matriz.setAlicuota(matrizDTO.getAlicuota());
        matriz.setAlicuotaLetras(matrizDTO.getAlicuotaLetras());
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

