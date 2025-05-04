package com.cramirez.backendcramirez.localizacion.infrastructure.web;

import com.cramirez.backendcramirez.dto.localizacion.ProvinciaDTO;
import com.cramirez.backendcramirez.service.localizacion.ProvinciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provincias")
public class ProvinciaController {

    private final ProvinciaService provinciaService;

    public ProvinciaController(ProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    @GetMapping("/listar")
    public List<ProvinciaDTO> listarProvincias() {
        return provinciaService.findAll();
    }

    @GetMapping("/{id}")
    public ProvinciaDTO obtenerProvinciaPorId(@PathVariable int id) {
        return provinciaService.findById(id).orElse(null);
    }

    @PostMapping("/guardar")
    public ProvinciaDTO guardarProvincia(@RequestBody ProvinciaDTO provinciaDTO) {
        return provinciaService.save(provinciaDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarProvincia(@PathVariable int id) {
        provinciaService.deleteById(id);
    }
}
