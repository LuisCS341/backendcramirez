package com.cramirez.backendcramirez.metadata.infrastructure.web;

import com.cramirez.backendcramirez.dto.metadata.MonedaDTO;
import com.cramirez.backendcramirez.service.metadata.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/monedas")
public class MonedaController {

    private final MonedaService monedaService;

    @Autowired
    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @GetMapping
    public ResponseEntity<List<MonedaDTO>> getAll() {
        return ResponseEntity.ok(monedaService.getAllMonedas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonedaDTO> getById(@PathVariable Integer id) {
        Optional<MonedaDTO> moneda = monedaService.getMonedaById(id);
        return moneda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MonedaDTO> create(@RequestBody MonedaDTO monedaDTO) {
        MonedaDTO nuevaMoneda = monedaService.saveMoneda(monedaDTO);
        return ResponseEntity.ok(nuevaMoneda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!monedaService.getMonedaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        monedaService.deleteMoneda(id);
        return ResponseEntity.noContent().build();
    }
}
