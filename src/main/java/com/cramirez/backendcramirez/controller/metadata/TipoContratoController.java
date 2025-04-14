package com.cramirez.backendcramirez.controller.metadata;

import com.cramirez.backendcramirez.dto.metadata.TipoContratoDTO;
import com.cramirez.backendcramirez.service.metadata.TipoContratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-contratos")
public class TipoContratoController {
    private final TipoContratoService tipoContratoService;

    public TipoContratoController(TipoContratoService tipoContratoService) {
        this.tipoContratoService = tipoContratoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoContratoDTO>> getAll() {
        return ResponseEntity.ok(tipoContratoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoContratoDTO> getById(@PathVariable int id) {
        Optional<TipoContratoDTO> tipoContrato = tipoContratoService.findById(id);
        return tipoContrato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoContratoDTO> create(@RequestBody TipoContratoDTO tipoContratoDTO) {
        TipoContratoDTO nuevoTipoContrato = tipoContratoService.save(tipoContratoDTO);
        return ResponseEntity.ok(nuevoTipoContrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoContratoDTO> update(@PathVariable int id, @RequestBody TipoContratoDTO tipoContratoDTO) {
        if (!tipoContratoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoContratoDTO.setIdTipoContrato(id);
        return ResponseEntity.ok(tipoContratoService.save(tipoContratoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!tipoContratoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoContratoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
