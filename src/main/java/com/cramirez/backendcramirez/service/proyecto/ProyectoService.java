package com.cramirez.backendcramirez.service.proyecto;

import com.cramirez.backendcramirez.dto.proyecto.ProyectoDTO;
import com.cramirez.backendcramirez.entity.proyecto.Proyecto;
import com.cramirez.backendcramirez.repository.proyecto.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<ProyectoDTO> findAll() {
        return proyectoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<ProyectoDTO> findById(int id) {
        return proyectoRepository.findById(id).map(this::convertToDTO);
    }

    public ProyectoDTO save(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = convertToEntity(proyectoDTO);
        return convertToDTO(proyectoRepository.save(proyecto));
    }

    public void deleteById(int id) {
        proyectoRepository.deleteById(id);
    }

    private ProyectoDTO convertToDTO(Proyecto proyecto) {
        return new ProyectoDTO(
                proyecto.getIdProyecto(),
                proyecto.getIdEmpresa(),
                proyecto.getIdMatriz(),
                proyecto.getNombreProyecto()

        );
    }

    private Proyecto convertToEntity(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(proyectoDTO.getIdProyecto());
        proyecto.setIdEmpresa(proyectoDTO.getIdEmpresa());
        proyecto.setIdMatriz(proyectoDTO.getIdMatriz());
        proyecto.setNombreProyecto(proyectoDTO.getNombreProyecto());
        return proyecto;
    }
}
