package com.cramirez.backendcramirez.service.proyecto;
import com.cramirez.backendcramirez.dto.proyecto.TipoProyectoDTO;
import com.cramirez.backendcramirez.entity.proyecto.TipoProyecto;
import com.cramirez.backendcramirez.repository.proyecto.TipoProyectoRepository;
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
        return dto;
    }

    private TipoProyecto toEntity(TipoProyectoDTO dto) {
        TipoProyecto tipoProyecto = new TipoProyecto();
        tipoProyecto.setIdTipoProyecto(dto.getIdTipoProyecto());
        tipoProyecto.setTipoProyecto(dto.getTipoProyecto());
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
