package com.cramirez.backendcramirez.controller.matriz;

import com.cramirez.backendcramirez.dto.matriz.MatrizDTO;
import com.cramirez.backendcramirez.service.matriz.MatrizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matrices")
public class MatrizController {

    private final MatrizService matrizService;

    @Autowired
    public MatrizController(MatrizService matrizService) {
        this.matrizService = matrizService;
    }

    @GetMapping
    public ResponseEntity<List<MatrizDTO>> getAllMatrices() {
        return ResponseEntity.ok(matrizService.getAllMatrices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatrizDTO> getMatrizById(@PathVariable Integer id) {
        Optional<MatrizDTO> matriz = matrizService.getMatrizById(id);
        return matriz.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MatrizDTO> createMatriz(@RequestBody MatrizDTO matrizDTO) {
        return ResponseEntity.ok(matrizService.saveMatriz(matrizDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatriz(@PathVariable Integer id) {
        matrizService.deleteMatriz(id);
        return ResponseEntity.noContent().build();
    }
}
