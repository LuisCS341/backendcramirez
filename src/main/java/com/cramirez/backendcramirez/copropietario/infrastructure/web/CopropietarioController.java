package com.cramirez.backendcramirez.copropietario.infrastructure.web;

import com.cramirez.backendcramirez.copropietario.application.service.CopropietarioService;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copropietario")
public class CopropietarioController {

    private final CopropietarioService copropietarioService;

    @Autowired
    public CopropietarioController(CopropietarioService copropietarioService) {
        this.copropietarioService = copropietarioService;
    }

    @GetMapping
    public ResponseEntity<List<CopropietarioDTO>> obtenerTodosLosCopropietarios() {
        List<CopropietarioDTO> copropietarios = copropietarioService.obtenerTodosLosCopropietarios();
        return ResponseEntity.ok(copropietarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CopropietarioDTO> obtenerCopropietarioPorId(@PathVariable int id) {
        CopropietarioDTO copropietario = copropietarioService.obtenerCopropietarioPorId(id);
        return copropietario != null ? ResponseEntity.ok(copropietario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearCopropietario(
            @RequestBody CopropietarioDTO copropietarioDTO,
            @RequestHeader("X-User-ID") Integer idOperarioCopropietarios) {

        if (idOperarioCopropietarios == null) {
            return ResponseEntity.badRequest().body("ID_Operario es requerido en el encabezado X-User-ID");
        }

        copropietarioDTO.setIdOperarioCopropietarios(idOperarioCopropietarios);

        CopropietarioDTO nuevoCopropietario = copropietarioService.crearCopropietario(copropietarioDTO);

        return ResponseEntity.ok(nuevoCopropietario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CopropietarioDTO> actualizarCopropietario(@PathVariable int id, @RequestBody CopropietarioDTO copropietarioDTO) {
        CopropietarioDTO copropietarioActualizado = copropietarioService.actualizarCopropietario(id, copropietarioDTO);
        return copropietarioActualizado != null ? ResponseEntity.ok(copropietarioActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCopropietario(@PathVariable int id) {
        copropietarioService.eliminarCopropietario(id);
        return ResponseEntity.noContent().build();
    }
}
