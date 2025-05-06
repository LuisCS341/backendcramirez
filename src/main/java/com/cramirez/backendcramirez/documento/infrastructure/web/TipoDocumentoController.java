package com.cramirez.backendcramirez.documento.infrastructure.web;
import com.cramirez.backendcramirez.documento.application.service.TipoDocumentoService;
import com.cramirez.backendcramirez.documento.domain.entity.TipoDocumento;
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
