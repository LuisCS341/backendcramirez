package com.cramirez.backendcramirez.contrato.application.service;
import com.cramirez.backendcramirez.contrato.domain.entity.Contrato;
import com.cramirez.backendcramirez.contrato.dto.ContratoDTO;
import com.cramirez.backendcramirez.contrato.infrastructure.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;

    @Autowired
    public ContratoService(ContratoRepository contratoRepository) {
        this.contratoRepository = contratoRepository;
    }

    public List<ContratoDTO> obtenerTodosLosContratos() {
        List<Contrato> contratos = contratoRepository.findAll();
        return contratos.stream().map(this::convertirA_DTO).collect(Collectors.toList());
    }

    public ContratoDTO obtenerContratoPorId(int id) {
        return contratoRepository.findById(id)
                .map(this::convertirA_DTO)
                .orElse(null);
    }

    public ContratoDTO crearContrato(ContratoDTO contratoDTO) {
        Contrato contrato = convertirA_Entidad(contratoDTO);
        Contrato contratoGuardado = contratoRepository.save(contrato);
        return convertirA_DTO(contratoGuardado);
    }

    public ContratoDTO actualizarContrato(int id, ContratoDTO contratoDTO) {
        return contratoRepository.findById(id)
                .map(contrato -> {
                    contrato.setIdContrato(contratoDTO.getIdContrato());
                    contrato.setIdCliente(contratoDTO.getIdCliente());
                    contrato.setIdTipoContrato(contratoDTO.getIdTipoContrato());
                    contrato.setIdLote(contratoDTO.getIdLote());
                    contrato.setNumeroContrato(contratoDTO.getNumeroContrato());
                    contrato.setFechaInicio(contratoDTO.getFechaInicio());
                    contrato.setFechaCanelacion(contratoDTO.getFechaCanelacion());
                    contrato.setMantenimientoMensual(contratoDTO.getMantenimientoMensual());
                    contrato.setMantenimientoLetras(contratoDTO.getMantenimientoLetras());
                    Contrato contratoActualizado = contratoRepository.save(contrato);
                    return convertirA_DTO(contratoActualizado);
                })
                .orElse(null);
    }

    public void eliminarContrato(int id) {
        contratoRepository.deleteById(id);
    }

    private ContratoDTO convertirA_DTO(Contrato contrato) {
        ContratoDTO dto = new ContratoDTO();
        dto.setIdContrato(contrato.getIdContrato());
        dto.setIdCliente(contrato.getIdCliente());
        dto.setIdTipoContrato(contrato.getIdTipoContrato());
        dto.setIdLote(contrato.getIdLote());
        dto.setFechaInicio(contrato.getFechaInicio());
        dto.setFechaCanelacion(contrato.getFechaCanelacion());
        dto.setMantenimientoMensual(contrato.getMantenimientoMensual());
        dto.setMantenimientoLetras(contrato.getMantenimientoLetras());
        return dto;
    }

    private Contrato convertirA_Entidad(ContratoDTO dto) {
        Contrato contrato = new Contrato();
        contrato.setIdContrato(dto.getIdContrato());
        contrato.setIdCliente(dto.getIdCliente());
        contrato.setIdTipoContrato(dto.getIdTipoContrato());
        contrato.setIdLote(dto.getIdLote());
        contrato.setFechaInicio(dto.getFechaInicio());
        contrato.setFechaCanelacion(dto.getFechaCanelacion());
        contrato.setMantenimientoMensual(dto.getMantenimientoMensual());
        contrato.setMantenimientoLetras(dto.getMantenimientoLetras());
        return contrato;
    }
}
