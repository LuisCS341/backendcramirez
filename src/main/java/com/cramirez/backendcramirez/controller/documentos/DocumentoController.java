package com.cramirez.backendcramirez.controller.documentos;

import com.cramirez.backendcramirez.dto.documentos.DocumentoDTO;
import com.cramirez.backendcramirez.service.documento.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    @Autowired
    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> obtenerTodosLosDocumentos() {
        List<DocumentoDTO> documentos = documentoService.obtenerTodosLosDocumentos();
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> obtenerDocumentoPorId(@PathVariable int id) {
        DocumentoDTO documento = documentoService.obtenerDocumentoPorId(id);
        return documento != null ? ResponseEntity.ok(documento) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DocumentoDTO> crearDocumento(@RequestBody DocumentoDTO documentoDTO) {
        DocumentoDTO nuevoDocumento = documentoService.crearDocumento(documentoDTO);
        return ResponseEntity.ok(nuevoDocumento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> actualizarDocumento(@PathVariable int id, @RequestBody DocumentoDTO documentoDTO) {
        DocumentoDTO documentoActualizado = documentoService.actualizarDocumento(id, documentoDTO);
        return documentoActualizado != null ? ResponseEntity.ok(documentoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable int id) {
        documentoService.eliminarDocumento(id);
        return ResponseEntity.noContent().build();
    }
}
