package com.cramirez.backendcramirez.metadata.infrastructure.web;

import com.cramirez.backendcramirez.dto.metadata.EstadoDTO;
import com.cramirez.backendcramirez.service.metadata.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    private final EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> getAllEstados() {
        return ResponseEntity.ok(estadoService.getAllEstados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> getEstadoById(@PathVariable Integer id) {
        Optional<EstadoDTO> estado = estadoService.getEstadoById(id);
        return estado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> createEstado(@RequestBody EstadoDTO estadoDTO) {
        return ResponseEntity.ok(estadoService.saveEstado(estadoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Integer id) {
        estadoService.deleteEstado(id);
        return ResponseEntity.noContent().build();
    }
}
