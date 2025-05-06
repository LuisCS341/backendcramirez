package com.cramirez.backendcramirez.copropietario.infrastructure.web;
import com.cramirez.backendcramirez.copropietario.application.service.CopropietarioConyugeService;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioConyugeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/copropietarioconyuge")
public class CopropietarioConyugeController {

    private final CopropietarioConyugeService copropietarioConyugeService;

    @Autowired
    public CopropietarioConyugeController(CopropietarioConyugeService copropietarioConyugeService) {
        this.copropietarioConyugeService = copropietarioConyugeService;
    }

    @PostMapping
    public ResponseEntity<?> crearCopropietarioConyuge(
            @RequestBody CopropietarioConyugeDTO copropietarioConyugeDTO,
            @RequestHeader("X-User-ID") Integer idOperarioCopropietarioConyuge) {

        if (idOperarioCopropietarioConyuge == null) {
            return ResponseEntity.badRequest().body("ID_Operario es requerido en el encabezado X-User-ID");
        }


        copropietarioConyugeDTO.setIdOperarioCopropietarioConyuge(idOperarioCopropietarioConyuge);

        CopropietarioConyugeDTO nuevoCopropietarioConyuge = copropietarioConyugeService.guardarCopropietarioConyuge(copropietarioConyugeDTO);

        return ResponseEntity.ok(nuevoCopropietarioConyuge);
    }


    @GetMapping
    public List<CopropietarioConyugeDTO> obtenerTodos() {
        return copropietarioConyugeService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public CopropietarioConyugeDTO obtenerPorId(@PathVariable int id) {
        return copropietarioConyugeService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public CopropietarioConyugeDTO actualizarCopropietarioConyuge(
            @PathVariable int id, @RequestBody CopropietarioConyugeDTO dto) {
        return copropietarioConyugeService.actualizarCopropietarioConyuge(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarCopropietarioConyuge(@PathVariable int id) {
        copropietarioConyugeService.eliminarCopropietarioConyuge(id);
    }
}
