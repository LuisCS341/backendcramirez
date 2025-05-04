package com.cramirez.backendcramirez.contrato.infrastructure.web;

import com.cramirez.backendcramirez.dto.contrato.ContratoDTO;
import com.cramirez.backendcramirez.service.contrato.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {

    private final ContratoService contratoService;

    @Autowired
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    @GetMapping
    public ResponseEntity<List<ContratoDTO>> obtenerTodosLosContratos() {
        List<ContratoDTO> contratos = contratoService.obtenerTodosLosContratos();
        return ResponseEntity.ok(contratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoDTO> obtenerContratoPorId(@PathVariable int id) {
        ContratoDTO contrato = contratoService.obtenerContratoPorId(id);
        return contrato != null ? ResponseEntity.ok(contrato) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ContratoDTO> crearContrato(@RequestBody ContratoDTO contratoDTO) {
        ContratoDTO nuevoContrato = contratoService.crearContrato(contratoDTO);
        return ResponseEntity.ok(nuevoContrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContratoDTO> actualizarContrato(@PathVariable int id, @RequestBody ContratoDTO contratoDTO) {
        ContratoDTO contratoActualizado = contratoService.actualizarContrato(id, contratoDTO);
        return contratoActualizado != null ? ResponseEntity.ok(contratoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable int id) {
        contratoService.eliminarContrato(id);
        return ResponseEntity.noContent().build();
    }
}
