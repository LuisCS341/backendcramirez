package com.cramirez.backendcramirez.metadata.infrastructure.web;
import com.cramirez.backendcramirez.metadata.application.service.TipoPagoService;
import com.cramirez.backendcramirez.metadata.dto.TipoPagoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-pagos")
public class TipoPagoController {
    private final TipoPagoService tipoPagoService;

    public TipoPagoController(TipoPagoService tipoPagoService) {
        this.tipoPagoService = tipoPagoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoPagoDTO>> getAll() {
        return ResponseEntity.ok(tipoPagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPagoDTO> getById(@PathVariable Long id) {
        Optional<TipoPagoDTO> tipoPago = tipoPagoService.findById(id);
        return tipoPago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoPagoDTO> create(@RequestBody TipoPagoDTO tipoPagoDTO) {
        TipoPagoDTO nuevoTipoPago = tipoPagoService.save(tipoPagoDTO);
        return ResponseEntity.ok(nuevoTipoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPagoDTO> update(@PathVariable Long id, @RequestBody TipoPagoDTO tipoPagoDTO) {
        if (!tipoPagoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoPagoDTO.setId(id);
        return ResponseEntity.ok(tipoPagoService.save(tipoPagoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!tipoPagoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoPagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
