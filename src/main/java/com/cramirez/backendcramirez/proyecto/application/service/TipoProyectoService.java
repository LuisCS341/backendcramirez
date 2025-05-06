package com.cramirez.backendcramirez.proyecto.application.service;
import com.cramirez.backendcramirez.proyecto.domain.entity.TipoProyecto;
import com.cramirez.backendcramirez.proyecto.dto.TipoProyectoDTO;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.TipoProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoProyectoService {

    @Autowired
    private TipoProyectoRepository tipoProyectoRepository;

    private TipoProyectoDTO toDTO(TipoProyecto tipoProyecto) {
        TipoProyectoDTO dto = new TipoProyectoDTO();
        dto.setIdTipoProyecto(tipoProyecto.getIdTipoProyecto());
        dto.setTipoProyecto(tipoProyecto.getTipoProyecto());
        dto.setEmpresaVende(tipoProyecto.getEmpresaVende());
        dto.setRucVendedor(tipoProyecto.getRucVendedor());
        dto.setDireccionVendedor(tipoProyecto.getDireccionVendedor());
        dto.setRepresentanteLegalVendedor(tipoProyecto.getRepresentanteLegalVendedor());
        dto.setDniVendedor(tipoProyecto.getDniVendedor());
        dto.setNPartidaPoderVendedor(tipoProyecto.getNPartidaPoderVendedor());
        dto.setMoneda(tipoProyecto.getMoneda());
        dto.setNumCuenta(tipoProyecto.getNumCuenta());
        dto.setCci(tipoProyecto.getCci());
        dto.setFechaSale(tipoProyecto.getFechaSale());
        dto.setFechaFirmaContratoDefinitivo(tipoProyecto.getFechaFirmaContratoDefinitivo());
        dto.setAreaMatrizHas(tipoProyecto.getAreaMatrizHas());
        dto.setRegistroDe(tipoProyecto.getRegistroDe());
        dto.setPartidaMatriz(tipoProyecto.getPartidaMatriz());
        dto.setUbicacionLote(tipoProyecto.getUbicacionLote());
        dto.setUnidadCatastralMatriz(tipoProyecto.getUnidadCatastralMatriz());
        dto.setUrbanizacionMatriz(tipoProyecto.getUrbanizacionMatriz());
        dto.setDistritoMatriz(tipoProyecto.getDistritoMatriz());
        dto.setProvinciaMatriz(tipoProyecto.getProvinciaMatriz());
        dto.setDepartamentoMatriz(tipoProyecto.getDepartamentoMatriz());
        dto.setCompraVentaMatriz(tipoProyecto.getCompraVentaMatriz());
        dto.setSituacionLegalMatriz(tipoProyecto.getSituacionLegalMatriz());

        return dto;
    }

    private TipoProyecto toEntity(TipoProyectoDTO dto) {
        TipoProyecto tipoProyecto = new TipoProyecto();
        tipoProyecto.setIdTipoProyecto(dto.getIdTipoProyecto());
        tipoProyecto.setTipoProyecto(dto.getTipoProyecto());
        tipoProyecto.setEmpresaVende(dto.getEmpresaVende());
        tipoProyecto.setRucVendedor(dto.getRucVendedor());
        tipoProyecto.setDireccionVendedor(dto.getDireccionVendedor());
        tipoProyecto.setRepresentanteLegalVendedor(dto.getRepresentanteLegalVendedor());
        tipoProyecto.setDniVendedor(dto.getDniVendedor());
        tipoProyecto.setNPartidaPoderVendedor(dto.getNPartidaPoderVendedor());
        tipoProyecto.setMoneda(dto.getMoneda());
        tipoProyecto.setNumCuenta(dto.getNumCuenta());
        tipoProyecto.setCci(dto.getCci());
        tipoProyecto.setFechaSale(dto.getFechaSale());
        tipoProyecto.setFechaFirmaContratoDefinitivo(dto.getFechaFirmaContratoDefinitivo());
        tipoProyecto.setAreaMatrizHas(dto.getAreaMatrizHas());
        tipoProyecto.setRegistroDe(dto.getRegistroDe());
        tipoProyecto.setPartidaMatriz(dto.getPartidaMatriz());
        tipoProyecto.setUbicacionLote(dto.getUbicacionLote());
        tipoProyecto.setUnidadCatastralMatriz(dto.getUnidadCatastralMatriz());
        tipoProyecto.setUrbanizacionMatriz(dto.getUrbanizacionMatriz());
        tipoProyecto.setDistritoMatriz(dto.getDistritoMatriz());
        tipoProyecto.setProvinciaMatriz(dto.getProvinciaMatriz());
        tipoProyecto.setDepartamentoMatriz(dto.getDepartamentoMatriz());
        tipoProyecto.setCompraVentaMatriz(dto.getCompraVentaMatriz());
        tipoProyecto.setSituacionLegalMatriz(dto.getSituacionLegalMatriz());
        return tipoProyecto;
    }

    public List<TipoProyectoDTO> listarTodos() {
        return tipoProyectoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TipoProyectoDTO> obtenerPorId(int id) {
        return tipoProyectoRepository.findById(id)
                .map(this::toDTO);
    }

    public TipoProyectoDTO guardar(TipoProyectoDTO dto) {
        TipoProyecto entidadGuardada = tipoProyectoRepository.save(toEntity(dto));
        return toDTO(entidadGuardada);
    }

    public void eliminar(int id) {
        tipoProyectoRepository.deleteById(id);
    }
}
