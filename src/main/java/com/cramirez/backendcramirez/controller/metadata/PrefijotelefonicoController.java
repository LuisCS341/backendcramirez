package com.cramirez.backendcramirez.controller.metadata;

import com.cramirez.backendcramirez.dto.metadata.PrefijotelefonicoDTO;
import com.cramirez.backendcramirez.service.metadata.PrefijotelefonicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prefijos")
public class PrefijotelefonicoController {
    @Autowired
    private PrefijotelefonicoService service;

    @GetMapping
    public List<PrefijotelefonicoDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrefijotelefonicoDTO> getById(@PathVariable Integer id) {
        Optional<PrefijotelefonicoDTO> prefijo = service.getById(id);
        return prefijo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PrefijotelefonicoDTO create(@RequestBody PrefijotelefonicoDTO prefijo) {
        return service.save(prefijo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
