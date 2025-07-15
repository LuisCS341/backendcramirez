package com.cramirez.backendcramirez.lote.infrastructure.web;

import com.cramirez.backendcramirez.lote.application.service.MatrizService;
import com.cramirez.backendcramirez.lote.dto.MatrizDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matriz")
public class MatrizController {

    private final MatrizService matrizService;

    @Autowired
    public MatrizController(MatrizService matrizService) {
        this.matrizService = matrizService;
    }

    @GetMapping
    public ResponseEntity<List<MatrizDTO>> getAllMatrices() {
        List<MatrizDTO> matrices = matrizService.getAllMatrices();
        return ResponseEntity.ok(matrices);
    }

    @GetMapping("/lote/{idLote}")
    public ResponseEntity<MatrizDTO> getMatrizByIdLote(@PathVariable Integer idLote) {
        Optional<MatrizDTO> matrizDTO = matrizService.getMatrizByIdLote(idLote);
        return matrizDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MatrizDTO> createMatriz(@RequestBody MatrizDTO matrizDTO) {
        MatrizDTO saved = matrizService.saveMatriz(matrizDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<MatrizDTO> updateMatriz(@RequestBody MatrizDTO matrizDTO) {
        MatrizDTO updated = matrizService.saveMatriz(matrizDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatriz(@PathVariable Integer id) {
        matrizService.deleteMatriz(id);
        return ResponseEntity.noContent().build();
    }
}
