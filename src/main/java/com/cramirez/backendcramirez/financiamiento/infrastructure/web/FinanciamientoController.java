package com.cramirez.backendcramirez.financiamiento.infrastructure.web;

import com.cramirez.backendcramirez.dto.financiamiento.FinanciamientoDTO;
import com.cramirez.backendcramirez.service.financiamiento.FinanciamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/financiamientos")
public class FinanciamientoController {

    @Autowired
    private FinanciamientoService financiamientoService;

    // Obtener todos los financiamientos en formato DTO
    @GetMapping
    public List<FinanciamientoDTO> getAll() {
        return financiamientoService.getAllFinanciamientos();
    }

    // Obtener financiamiento por ID en formato DTO
    @GetMapping("/{id}")
    public Optional<FinanciamientoDTO> getById(@PathVariable Integer id) {
        return financiamientoService.getFinanciamientoById(id);
    }

    // Crear o actualizar financiamiento desde un DTO
    @PostMapping
    public FinanciamientoDTO create(@RequestBody FinanciamientoDTO financiamientoDTO) {
        return financiamientoService.saveFinanciamiento(financiamientoDTO);
    }

    // Eliminar financiamiento por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        financiamientoService.deleteFinanciamiento(id);
    }
}
