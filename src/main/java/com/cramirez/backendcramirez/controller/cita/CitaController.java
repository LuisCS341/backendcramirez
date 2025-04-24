package com.cramirez.backendcramirez.controller.cita;

import com.cramirez.backendcramirez.dto.cita.CitaDTO;
import com.cramirez.backendcramirez.service.cita.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> obtenerTodasLasCitas() {
        List<CitaDTO> citas = citaService.obtenerTodasLasCitas();
        return citas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(citas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> obtenerCitaPorId(@PathVariable int id) {
        CitaDTO citaDTO = citaService.obtenerCitaPorId(id);
        return citaDTO != null
                ? ResponseEntity.ok(citaDTO)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CitaDTO> crearCita(@RequestBody CitaDTO citaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(citaService.guardarCita(citaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> actualizarCita(@PathVariable int id,@RequestBody CitaDTO citaDTO) {
        CitaDTO citaActualizada = citaService.actualizarCita(id, citaDTO);
        return citaActualizada != null
                ? ResponseEntity.ok(citaActualizada)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable int id) {
        if (citaService.obtenerCitaPorId(id) != null) {
            citaService.eliminarCita(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
