package com.cramirez.backendcramirez.metadata.application.service;
import com.cramirez.backendcramirez.metadata.domain.entity.Moneda;
import com.cramirez.backendcramirez.metadata.dto.MonedaDTO;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonedaService {

    private final MonedaRepository monedaRepository;

    @Autowired
    public MonedaService(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public List<MonedaDTO> getAllMonedas() {
        List<Moneda> monedas = monedaRepository.findAll();
        return monedas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<MonedaDTO> getMonedaById(Integer id) {
        return monedaRepository.findById(id).map(this::convertToDTO);
    }

    public MonedaDTO saveMoneda(MonedaDTO monedaDTO) {
        Moneda moneda = convertToEntity(monedaDTO);
        Moneda savedMoneda = monedaRepository.save(moneda);
        return convertToDTO(savedMoneda);
    }

    public void deleteMoneda(Integer id) {
        monedaRepository.deleteById(id);
    }

    private MonedaDTO convertToDTO(Moneda moneda) {
        MonedaDTO dto = new MonedaDTO();
        dto.setIdMoneda(moneda.getIdMoneda());
        dto.setMonedas(moneda.getMonedas());
        return dto;
    }

    private Moneda convertToEntity(MonedaDTO dto) {
        Moneda moneda = new Moneda();
        moneda.setIdMoneda(dto.getIdMoneda());
        moneda.setMonedas(dto.getMonedas());
        return moneda;
    }
}
