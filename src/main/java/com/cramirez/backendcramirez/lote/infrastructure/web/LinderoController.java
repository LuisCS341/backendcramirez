package com.cramirez.backendcramirez.lote.infrastructure.web;

import com.cramirez.backendcramirez.dto.lote.LinderoDTO;
import com.cramirez.backendcramirez.service.lote.LinderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lindero")
public class LinderoController {

    private final LinderoService linderoService;

    @Autowired
    public LinderoController(LinderoService linderoService) {
        this.linderoService = linderoService;
    }

    @GetMapping
    public ResponseEntity<List<LinderoDTO>> getAllLinderos() {
        return ResponseEntity.ok(linderoService.getAllLinderos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinderoDTO> getLinderoById(@PathVariable Integer id) {
        Optional<LinderoDTO> lindero = linderoService.getLinderoById(id);
        return lindero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LinderoDTO> createLindero(@RequestBody LinderoDTO linderoDTO) {
        return ResponseEntity.ok(linderoService.saveLindero(linderoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLindero(@PathVariable Integer id) {
        linderoService.deleteLindero(id);
        return ResponseEntity.noContent().build();
    }
}
