package com.cramirez.backendcramirez.cita.application.service;

import com.cramirez.backendcramirez.cita.dto.CitaDTO;
import com.cramirez.backendcramirez.cita.domain.entity.Cita;
import com.cramirez.backendcramirez.cita.infrastructure.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<CitaDTO> obtenerTodasLasCitas() {
        List<Cita> citas = citaRepository.findAll();
        return citas.stream().map(this::convertirA_DTO).collect(Collectors.toList());
    }

    public CitaDTO obtenerCitaPorId(int id) {
        return citaRepository.findById(id)
                .map(this::convertirA_DTO)
                .orElse(null);
    }

    public CitaDTO guardarCita(CitaDTO citaDTO) {
        Cita cita = convertirA_Entidad(citaDTO);
        Cita citaGuardada = citaRepository.save(cita);
        return convertirA_DTO(citaGuardada);
    }

    public void eliminarCita(int id) {
        citaRepository.deleteById(id);
    }

    public CitaDTO crearCita(CitaDTO citaDTO) {
        Cita cita = convertirA_Entidad(citaDTO);
        Cita citaGuardada = citaRepository.save(cita);
        return convertirA_DTO(citaGuardada);
    }

    public CitaDTO actualizarCita(int id, CitaDTO citaDTO) {
        return citaRepository.findById(id)
                .map(cita -> {
                    cita.setIdCliente(citaDTO.getIdCliente());
                    cita.setFechaCita(citaDTO.getFechaCita());
                    cita.setHoraCita(citaDTO.getHoraCita());
                    cita.setPersonaAtiende(citaDTO.getPersonaAtiende());
                    Cita citaActualizada = citaRepository.save(cita);
                    return convertirA_DTO(citaActualizada);
                })
                .orElse(null);
    }

    private CitaDTO convertirA_DTO(Cita cita) {
        CitaDTO dto = new CitaDTO();
        dto.setIdCita(cita.getIdCita());
        dto.setIdCliente(cita.getIdCliente());
        dto.setFechaCita(cita.getFechaCita());
        dto.setHoraCita(cita.getHoraCita());
        dto.setPersonaAtiende(cita.getPersonaAtiende());
        return dto;
    }

    private Cita convertirA_Entidad(CitaDTO dto) {
        Cita cita = new Cita();
        cita.setIdCita(dto.getIdCita());
        cita.setIdCliente(dto.getIdCliente());
        cita.setFechaCita(dto.getFechaCita());
        cita.setHoraCita(dto.getHoraCita());
        cita.setPersonaAtiende(dto.getPersonaAtiende());
        return cita;
    }
}
