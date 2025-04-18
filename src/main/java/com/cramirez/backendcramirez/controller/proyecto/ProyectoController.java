package com.cramirez.backendcramirez.controller.proyecto;

import com.cramirez.backendcramirez.dto.proyecto.ProyectoDTO;
import com.cramirez.backendcramirez.service.proyecto.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> getAll() {
        return ResponseEntity.ok(proyectoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> getById(@PathVariable int id) {
        Optional<ProyectoDTO> proyecto = proyectoService.findById(id);
        return proyecto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> create(@RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO nuevoProyecto = proyectoService.save(proyectoDTO);
        return ResponseEntity.ok(nuevoProyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO> update(@PathVariable int id, @RequestBody ProyectoDTO proyectoDTO) {
        if (!proyectoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        proyectoDTO.setIdProyecto(id);
        return ResponseEntity.ok(proyectoService.save(proyectoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!proyectoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        proyectoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
