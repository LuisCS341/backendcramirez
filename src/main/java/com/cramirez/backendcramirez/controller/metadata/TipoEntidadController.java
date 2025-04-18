package com.cramirez.backendcramirez.controller.metadata;

import com.cramirez.backendcramirez.dto.metadata.TipoEntidadDTO;
import com.cramirez.backendcramirez.service.metadata.TipoEntidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-entidades")
public class TipoEntidadController {
    private final TipoEntidadService tipoEntidadService;

    public TipoEntidadController(TipoEntidadService tipoEntidadService) {
        this.tipoEntidadService = tipoEntidadService;
    }

    @GetMapping
    public ResponseEntity<List<TipoEntidadDTO>> getAll() {
        return ResponseEntity.ok(tipoEntidadService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEntidadDTO> getById(@PathVariable Long id) {
        Optional<TipoEntidadDTO> tipoEntidad = tipoEntidadService.findById(id);
        return tipoEntidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoEntidadDTO> create(@RequestBody TipoEntidadDTO tipoEntidadDTO) {
        TipoEntidadDTO nuevoTipoEntidad = tipoEntidadService.save(tipoEntidadDTO);
        return ResponseEntity.ok(nuevoTipoEntidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEntidadDTO> update(@PathVariable Long id, @RequestBody TipoEntidadDTO tipoEntidadDTO) {
        if (!tipoEntidadService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoEntidadDTO.setId(id);
        return ResponseEntity.ok(tipoEntidadService.save(tipoEntidadDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!tipoEntidadService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoEntidadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
