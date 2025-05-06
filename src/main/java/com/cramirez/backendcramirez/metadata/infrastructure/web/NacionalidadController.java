package com.cramirez.backendcramirez.metadata.infrastructure.web;
import com.cramirez.backendcramirez.metadata.application.service.NacionalidadService;
import com.cramirez.backendcramirez.metadata.dto.NacionalidadDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nacionalidades")
public class NacionalidadController {

    private final NacionalidadService nacionalidadService;

    public NacionalidadController(NacionalidadService nacionalidadService) {
        this.nacionalidadService = nacionalidadService;
    }

    @GetMapping("/listar")
    public List<NacionalidadDTO> listarNacionalidades() {
        return nacionalidadService.findAll();
    }

    @GetMapping("/{id}")
    public NacionalidadDTO obtenerNacionalidadPorId(@PathVariable int id) {  // Cambiado a int
        return nacionalidadService.findById(id).orElse(null);
    }

    @PostMapping("/guardar")
    public NacionalidadDTO guardarNacionalidad(@RequestBody NacionalidadDTO nacionalidadDTO) {
        return nacionalidadService.save(nacionalidadDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarNacionalidad(@PathVariable int id) {  // Cambiado a int
        nacionalidadService.deleteById(id);
    }
}
