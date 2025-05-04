package com.cramirez.backendcramirez.empresa.application.service;

import com.cramirez.backendcramirez.dto.empresa.EmpresaDTO;
import com.cramirez.backendcramirez.entity.empresa.Empresa;
import com.cramirez.backendcramirez.repository.empresa.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<EmpresaDTO> obtenerTodasLasEmpresas() {
        return empresaRepository.findAll().stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO obtenerEmpresaPorId(int id) {
        return empresaRepository.findById(id)
                .map(this::convertirA_DTO)
                .orElse(null);
    }

    public EmpresaDTO crearEmpresa(EmpresaDTO empresaDTO) {
        Empresa empresa = convertirA_Entidad(empresaDTO);
        Empresa empresaGuardada = empresaRepository.save(empresa);
        return convertirA_DTO(empresaGuardada);
    }

    public EmpresaDTO actualizarEmpresa(int id, EmpresaDTO empresaDTO) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setIdEmpresa(empresaDTO.getIdEmpresa());
                    empresa.setIdMoneda(empresaDTO.getIdMoneda());
                    empresa.setNombreEmpresa(empresaDTO.getNombreEmpresa());
                    empresa.setRuc(empresaDTO.getRuc());
                    empresa.setDireccion(empresaDTO.getDireccion());
                    empresa.setRepresentanteLegal(empresaDTO.getRepresentanteLegal());
                    empresa.setDniVendedor(empresaDTO.getDniVendedor());
                    empresa.setNPartidaEmpresa(empresaDTO.getNPartidaEmpresa());
                    empresa.setNCuenta(empresaDTO.getNCuenta());
                    empresa.setCCI(empresaDTO.getCCI());
                    empresa.setFechaEntrega(empresaDTO.getFechaEntrega());
                    empresa.setFechaFirmaContrato(empresaDTO.getFechaFirmaContrato());
                    Empresa empresaActualizada = empresaRepository.save(empresa);
                    return convertirA_DTO(empresaActualizada);
                })
                .orElse(null);
    }

    public void eliminarEmpresa(int id) {
        empresaRepository.deleteById(id);
    }

    private EmpresaDTO convertirA_DTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setIdEmpresa(empresa.getIdEmpresa());
        dto.setIdMoneda(empresa.getIdMoneda());
        dto.setNombreEmpresa(empresa.getNombreEmpresa());
        dto.setRuc(empresa.getRuc());
        dto.setDireccion(empresa.getDireccion());
        dto.setRepresentanteLegal(empresa.getRepresentanteLegal());
        dto.setDniVendedor(empresa.getDniVendedor());
        dto.setNPartidaEmpresa(empresa.getNPartidaEmpresa());
        dto.setNCuenta(empresa.getNCuenta());
        dto.setCCI(empresa.getCCI());
        dto.setFechaEntrega(empresa.getFechaEntrega());
        dto.setFechaFirmaContrato(empresa.getFechaFirmaContrato());
        return dto;
    }

    private Empresa convertirA_Entidad(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(dto.getIdEmpresa());
        empresa.setIdMoneda(dto.getIdMoneda());
        empresa.setNombreEmpresa(dto.getNombreEmpresa());
        empresa.setRuc(dto.getRuc());
        empresa.setDireccion(dto.getDireccion());
        empresa.setRepresentanteLegal(dto.getRepresentanteLegal());
        empresa.setDniVendedor(dto.getDniVendedor());
        empresa.setNPartidaEmpresa(dto.getNPartidaEmpresa());
        empresa.setNCuenta(dto.getNCuenta());
        empresa.setCCI(dto.getCCI());
        empresa.setFechaEntrega(dto.getFechaEntrega());
        empresa.setFechaFirmaContrato(dto.getFechaFirmaContrato());
        return empresa;
    }
}
