package com.cramirez.backendcramirez.localizacion.infrastructure.web;

import com.cramirez.backendcramirez.localizacion.application.service.DistritoService;
import com.cramirez.backendcramirez.localizacion.dto.DistritoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/distritos")
public class DistritoController {

    private final DistritoService distritoService;

    public DistritoController(DistritoService distritoService) {
        this.distritoService = distritoService;
    }

    @GetMapping
    public ResponseEntity<List<DistritoDTO>> getAllDistritos() {
        return ResponseEntity.ok(distritoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistritoDTO> getDistritoById(@PathVariable int id) {
        Optional<DistritoDTO> distrito = distritoService.findById(id);
        return distrito.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DistritoDTO> createDistrito(@RequestBody DistritoDTO distritoDTO) {
        DistritoDTO savedDistrito = distritoService.save(distritoDTO);
        return ResponseEntity.ok(savedDistrito);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrito(@PathVariable int id) {
        distritoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
