package com.cramirez.backendcramirez.documento.application.service;

import com.cramirez.backendcramirez.documento.dto.IdentificacionDTO;
import com.cramirez.backendcramirez.documento.infrastructure.repository.IdentificacionRepository;
import com.cramirez.backendcramirez.entity.documento.Identificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IdentificacionService {

    @Autowired
    private IdentificacionRepository identificacionRepository;

    public List<IdentificacionDTO> getAllIdentificaciones() {
        List<Identificacion> identificaciones = identificacionRepository.findAll();
        return identificaciones.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<IdentificacionDTO> getIdentificacionById(Integer id) {
        return identificacionRepository.findById(id).map(this::convertToDTO);
    }

    public IdentificacionDTO saveIdentificacion(IdentificacionDTO identificacionDTO) {
        Identificacion identificacion = convertToEntity(identificacionDTO);
        Identificacion saved = identificacionRepository.save(identificacion);
        return convertToDTO(saved);
    }

    public void deleteIdentificacion(Integer id) {
        identificacionRepository.deleteById(id);
    }

    private IdentificacionDTO convertToDTO(Identificacion identificacion) {
        IdentificacionDTO dto = new IdentificacionDTO();
        dto.setIdDocumentoIdentificacion(identificacion.getIdDocumentoIdentificacion());
        dto.setDocumentoIdentificacion(identificacion.getDocumentoIdentificacion());
        return dto;
    }

    private Identificacion convertToEntity(IdentificacionDTO dto) {
        Identificacion identificacion = new Identificacion();
        identificacion.setIdDocumentoIdentificacion(dto.getIdDocumentoIdentificacion());
        identificacion.setDocumentoIdentificacion(dto.getDocumentoIdentificacion());
        return identificacion;
    }
}
