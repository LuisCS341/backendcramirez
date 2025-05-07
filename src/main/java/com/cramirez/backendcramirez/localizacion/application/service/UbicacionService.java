package com.cramirez.backendcramirez.localizacion.application.service;
import com.cramirez.backendcramirez.localizacion.domain.entity.Ubicacion;
import com.cramirez.backendcramirez.localizacion.dto.UbicacionDTO;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    // Obtener todas las ubicaciones en formato DTO
    public List<UbicacionDTO> getAllUbicaciones() {
        List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
        return ubicaciones.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener ubicación por ID en formato DTO
    public Optional<UbicacionDTO> getUbicacionById(Integer id) {
        return ubicacionRepository.findById(id).map(this::convertToDTO);
    }

    // Guardar ubicación desde un DTO
    public UbicacionDTO saveUbicacion(UbicacionDTO ubicacionDTO) {
        Ubicacion ubicacion = convertToEntity(ubicacionDTO);
        Ubicacion savedEntity = ubicacionRepository.save(ubicacion);
        return convertToDTO(savedEntity);
    }

    // Eliminar ubicación por ID
    public void deleteUbicacion(Integer id) {
        ubicacionRepository.deleteById(id);
    }

    // Métodos de conversión entre DTO y Entidad
    private UbicacionDTO convertToDTO(Ubicacion ubicacion) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setIdUbicacion(ubicacion.getIdUbicacion());
        dto.setUbicacion(ubicacion.getUbicacion());
        dto.setEmpresaVende(ubicacion.getEmpresaVende());
        dto.setRucVendedor(ubicacion.getRucVendedor());
        dto.setDireccionVendedor(ubicacion.getDireccionVendedor());
        dto.setRepresentanteLegalVendedor(ubicacion.getRepresentanteLegalVendedor());
        dto.setDniVendedor(ubicacion.getDniVendedor());
        dto.setNPartidaPoderVendedor(ubicacion.getNPartidaPoderVendedor());
        dto.setMoneda(ubicacion.getMoneda());
        dto.setNumCuenta(ubicacion.getNumCuenta());
        dto.setCci(ubicacion.getCci());
        dto.setFechaSale(ubicacion.getFechaSale());
        dto.setFechaFirmaContratoDefinitivo(ubicacion.getFechaFirmaContratoDefinitivo());
        dto.setAreaMatrizHas(ubicacion.getAreaMatrizHas());
        dto.setRegistroDe(ubicacion.getRegistroDe());
        dto.setPartidaMatriz(ubicacion.getPartidaMatriz());
        dto.setUnidadCatastralMatriz(ubicacion.getUnidadCatastralMatriz());
        dto.setUrbanizacionMatriz(ubicacion.getUrbanizacionMatriz());
        dto.setDistritoMatriz(ubicacion.getDistritoMatriz());
        dto.setProvinciaMatriz(ubicacion.getProvinciaMatriz());
        dto.setDepartamentoMatriz(ubicacion.getDepartamentoMatriz());
        dto.setCompraVentaMatriz(ubicacion.getCompraVentaMatriz());
        dto.setSituacionLegalMatriz(ubicacion.getSituacionLegalMatriz());
        return dto;
    }

    private Ubicacion convertToEntity(UbicacionDTO dto) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdUbicacion(dto.getIdUbicacion());
        ubicacion.setUbicacion(dto.getUbicacion());
        ubicacion.setEmpresaVende(dto.getEmpresaVende());
        ubicacion.setRucVendedor(dto.getRucVendedor());
        ubicacion.setDireccionVendedor(dto.getDireccionVendedor());
        ubicacion.setRepresentanteLegalVendedor(dto.getRepresentanteLegalVendedor());
        ubicacion.setDniVendedor(dto.getDniVendedor());
        ubicacion.setNPartidaPoderVendedor(dto.getNPartidaPoderVendedor());
        ubicacion.setMoneda(dto.getMoneda());
        ubicacion.setNumCuenta(dto.getNumCuenta());
        ubicacion.setCci(dto.getCci());
        ubicacion.setFechaSale(dto.getFechaSale());
        ubicacion.setFechaFirmaContratoDefinitivo(dto.getFechaFirmaContratoDefinitivo());
        ubicacion.setAreaMatrizHas(dto.getAreaMatrizHas());
        ubicacion.setRegistroDe(dto.getRegistroDe());
        ubicacion.setPartidaMatriz(dto.getPartidaMatriz());
        ubicacion.setUnidadCatastralMatriz(dto.getUnidadCatastralMatriz());
        ubicacion.setUrbanizacionMatriz(dto.getUrbanizacionMatriz());
        ubicacion.setDistritoMatriz(dto.getDistritoMatriz());
        ubicacion.setProvinciaMatriz(dto.getProvinciaMatriz());
        ubicacion.setDepartamentoMatriz(dto.getDepartamentoMatriz());
        ubicacion.setCompraVentaMatriz(dto.getCompraVentaMatriz());
        ubicacion.setSituacionLegalMatriz(dto.getSituacionLegalMatriz());
        return ubicacion;
    }
}
