package com.cramirez.backendcramirez.controller.documentos;

import com.cramirez.backendcramirez.entity.documento.TipoDocumento;
import com.cramirez.backendcramirez.service.documento.TipoDocumentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-documento")
public class TipoDocumentoController {
    private final TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoController(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @GetMapping
    public List<TipoDocumento> getAll() {
        return tipoDocumentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumento> getById(@PathVariable int id) {
        Optional<TipoDocumento> tipoDocumento = tipoDocumentoService.findById(id);
        return tipoDocumento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoDocumento create(@RequestBody TipoDocumento tipoDocumento) {
        return tipoDocumentoService.save(tipoDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        tipoDocumentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
