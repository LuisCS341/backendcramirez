package com.cramirez.backendcramirez.operario.infrastructure.web;
import com.cramirez.backendcramirez.operario.application.service.OperarioService;
import com.cramirez.backendcramirez.operario.dto.OperarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operarios")
public class OperarioController {
    private final OperarioService operarioService;

    public OperarioController(OperarioService operarioService) {
        this.operarioService = operarioService;
    }

    @GetMapping
    public ResponseEntity<List<OperarioDTO>> getAll() {
        return ResponseEntity.ok(operarioService.findAll());
    }

    @GetMapping("/{idOperario}")
    public ResponseEntity<OperarioDTO> getById(@PathVariable int idOperario) {
        try {
            return ResponseEntity.ok(operarioService.findById(idOperario));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OperarioDTO> create(@RequestBody OperarioDTO operarioDTO) {
        OperarioDTO nuevoOperario = operarioService.save(operarioDTO);
        return ResponseEntity.ok(nuevoOperario);
    }

    @PutMapping("/{idOperario}")
    public ResponseEntity<OperarioDTO> update(@PathVariable int idOperario, @RequestBody OperarioDTO operarioDTO) {
        try {
            operarioService.findById(idOperario); // Verifica si existe
            operarioDTO.setIdOperario(idOperario);
            return ResponseEntity.ok(operarioService.save(operarioDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idOperario}")
    public ResponseEntity<Void> delete(@PathVariable int idOperario) {
        try {
            operarioService.findById(idOperario); // Verifica si existe
            operarioService.deleteById(idOperario);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
