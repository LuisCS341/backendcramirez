package com.cramirez.backendcramirez.metadata.infrastructure.web;
import com.cramirez.backendcramirez.metadata.application.service.TipoService;
import com.cramirez.backendcramirez.metadata.dto.TipoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo")
public class TipoController {

    private final TipoService tipoService;

    @Autowired
    public TipoController(TipoService tipoService) {
        this.tipoService = tipoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoDTO>> getAllTipos() {
        return ResponseEntity.ok(tipoService.getAllTipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> getTipoById(@PathVariable Integer id) {
        Optional<TipoDTO> tipo = tipoService.getTipoById(id);
        return tipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoDTO> createTipo(@RequestBody TipoDTO tipoDTO) {
        return ResponseEntity.ok(tipoService.saveTipo(tipoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipo(@PathVariable Integer id) {
        tipoService.deleteTipo(id);
        return ResponseEntity.noContent().build();
    }
}
