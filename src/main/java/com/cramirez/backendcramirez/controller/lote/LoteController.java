package com.cramirez.backendcramirez.controller.lote;

import com.cramirez.backendcramirez.dto.lote.LoteDTO;
import com.cramirez.backendcramirez.service.lote.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService loteService;

    @Autowired
    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public ResponseEntity<List<LoteDTO>> getAll() {
        return ResponseEntity.ok(loteService.getAllLotes());
    }

    @GetMapping("/ultimo")
    public ResponseEntity<Integer> getUltimoLote() {
        return loteService.getUltimoLote()
                .map(LoteDTO::getIdLote)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }


    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody LoteDTO loteDTO,
            @RequestHeader("X-User-ID") Integer idOperario) {

        if (idOperario == null) {
            return ResponseEntity.badRequest().body("ID_Operario es requerido en el encabezado X-User-ID");
        }

        loteDTO.setIdOperario(idOperario);
        LoteDTO nuevoLote = loteService.saveLote(loteDTO);
        return ResponseEntity.ok(nuevoLote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!loteService.getLoteById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        loteService.deleteLote(id);
        return ResponseEntity.noContent().build();
    }


}
