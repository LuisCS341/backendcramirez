package com.cramirez.backendcramirez.controller.metadata;

import com.cramirez.backendcramirez.dto.metadata.EstadoCivilDTO;
import com.cramirez.backendcramirez.service.metadata.EstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados-civiles")
public class EstadoCivilController {

    private final EstadoCivilService estadoCivilService;

    @Autowired
    public EstadoCivilController(EstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoCivilDTO>> obtenerTodos() {
        List<EstadoCivilDTO> estadosCiviles = estadoCivilService.obtenerTodos();
        return ResponseEntity.ok(estadosCiviles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCivilDTO> obtenerPorId(@PathVariable int id) {
        EstadoCivilDTO estadoCivil = estadoCivilService.obtenerPorId(id);
        return estadoCivil != null ? ResponseEntity.ok(estadoCivil) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EstadoCivilDTO> crear(@RequestBody EstadoCivilDTO estadoCivilDTO) {
        EstadoCivilDTO nuevoEstadoCivil = estadoCivilService.crear(estadoCivilDTO);
        return ResponseEntity.ok(nuevoEstadoCivil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoCivilDTO> actualizar(@PathVariable int id, @RequestBody EstadoCivilDTO estadoCivilDTO) {
        EstadoCivilDTO actualizado = estadoCivilService.actualizar(id, estadoCivilDTO);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        estadoCivilService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
