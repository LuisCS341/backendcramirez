package com.cramirez.backendcramirez.controller.documentos;

import com.cramirez.backendcramirez.dto.documentos.IdentificacionDTO;
import com.cramirez.backendcramirez.service.documento.IdentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/identificaciones")
public class IdentificacionController {

    @Autowired
    private IdentificacionService identificacionService;

    @GetMapping
    public ResponseEntity<List<IdentificacionDTO>> getAll() {
        return ResponseEntity.ok(identificacionService.getAllIdentificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentificacionDTO> getById(@PathVariable Integer id) {
        Optional<IdentificacionDTO> identificacion = identificacionService.getIdentificacionById(id);
        return identificacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IdentificacionDTO> create(@RequestBody IdentificacionDTO identificacionDTO) {
        IdentificacionDTO nuevaIdentificacion = identificacionService.saveIdentificacion(identificacionDTO);
        return ResponseEntity.ok(nuevaIdentificacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!identificacionService.getIdentificacionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        identificacionService.deleteIdentificacion(id);
        return ResponseEntity.noContent().build();
    }
}
