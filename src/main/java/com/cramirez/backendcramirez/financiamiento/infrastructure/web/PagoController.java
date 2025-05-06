package com.cramirez.backendcramirez.financiamiento.infrastructure.web;
import com.cramirez.backendcramirez.financiamiento.application.service.PagoService;
import com.cramirez.backendcramirez.financiamiento.dto.PagoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAll() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getById(@PathVariable Long id) {
        Optional<PagoDTO> pago = pagoService.findById(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PagoDTO> create(@RequestBody PagoDTO pagoDTO) {
        PagoDTO nuevoPago = pagoService.save(pagoDTO);
        return ResponseEntity.ok(nuevoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        if (!pagoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pagoDTO.setId(id);
        return ResponseEntity.ok(pagoService.save(pagoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!pagoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
