package com.cramirez.backendcramirez.localizacion.infrastructure.web;
import com.cramirez.backendcramirez.localizacion.application.service.UbicacionService;
import com.cramirez.backendcramirez.localizacion.dto.UbicacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    // Obtener todas las ubicaciones
    @GetMapping
    public ResponseEntity<List<UbicacionDTO>> getAllUbicaciones() {
        List<UbicacionDTO> ubicaciones = ubicacionService.getAllUbicaciones();
        return ResponseEntity.ok(ubicaciones);
    }

    // Obtener ubicación por ID
    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getUbicacionById(@PathVariable Integer id) {
        Optional<UbicacionDTO> ubicacion = ubicacionService.getUbicacionById(id);
        return ubicacion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Guardar una nueva ubicación
    @PostMapping
    public ResponseEntity<UbicacionDTO> saveUbicacion(@RequestBody UbicacionDTO ubicacionDTO) {
        UbicacionDTO savedUbicacion = ubicacionService.saveUbicacion(ubicacionDTO);
        return ResponseEntity.status(201).body(savedUbicacion);  // 201 Created
    }

    // Eliminar una ubicación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Integer id) {
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }
}
