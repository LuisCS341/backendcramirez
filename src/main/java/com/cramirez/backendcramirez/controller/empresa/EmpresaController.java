package com.cramirez.backendcramirez.controller.empresa;

import com.cramirez.backendcramirez.dto.empresa.EmpresaDTO;
import com.cramirez.backendcramirez.service.empresa.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> obtenerTodasLasEmpresas() {
        List<EmpresaDTO> empresas = empresaService.obtenerTodasLasEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obtenerEmpresaPorId(@PathVariable int id) {
        EmpresaDTO empresa = empresaService.obtenerEmpresaPorId(id);
        return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> crearEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nuevaEmpresa = empresaService.crearEmpresa(empresaDTO);
        return ResponseEntity.ok(nuevaEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizarEmpresa(@PathVariable int id, @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO empresaActualizada = empresaService.actualizarEmpresa(id, empresaDTO);
        return empresaActualizada != null ? ResponseEntity.ok(empresaActualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable int id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }
}
