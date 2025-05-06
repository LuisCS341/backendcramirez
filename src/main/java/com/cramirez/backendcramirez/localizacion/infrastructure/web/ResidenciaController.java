package com.cramirez.backendcramirez.localizacion.infrastructure.web;
import com.cramirez.backendcramirez.localizacion.application.service.ResidenciaService;
import com.cramirez.backendcramirez.localizacion.dto.ResidenciaDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residencia")
public class ResidenciaController {
    private final ResidenciaService residenciaService;

    public ResidenciaController(ResidenciaService residenciaService) {
        this.residenciaService = residenciaService;
    }

    @GetMapping
    public List<ResidenciaDTO> getAllResidencias() {
        return residenciaService.getAllResidencias();
    }

    @GetMapping("/{id}")
    public ResidenciaDTO getResidenciaById(@PathVariable int id) {
        return residenciaService.getResidenciaById(id);
    }

    @PostMapping
    public ResidenciaDTO createResidencia(@RequestBody ResidenciaDTO residencia) {
        return residenciaService.createResidencia(residencia);
    }
}