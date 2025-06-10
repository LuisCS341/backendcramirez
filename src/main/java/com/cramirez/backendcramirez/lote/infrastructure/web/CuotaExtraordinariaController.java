package com.cramirez.backendcramirez.lote.infrastructure.web;

import com.cramirez.backendcramirez.lote.application.service.CuotaExtraordinariaService;
import com.cramirez.backendcramirez.lote.dto.CuotaExtraordinariaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuotaextraordinaria")
public class CuotaExtraordinariaController {

    @Autowired
    private final CuotaExtraordinariaService cuotaExtraordinariaService;

    public CuotaExtraordinariaController(CuotaExtraordinariaService cuotaExtraordinariaService) {
        this.cuotaExtraordinariaService = cuotaExtraordinariaService;
    }

    @GetMapping
    public List<CuotaExtraordinariaDTO> getAll() {
        return cuotaExtraordinariaService.getAll();
    }

    @GetMapping("/{id}")
    public CuotaExtraordinariaDTO getById(@PathVariable int id) {
        return cuotaExtraordinariaService.getById(id);
    }

    @PostMapping
    public CuotaExtraordinariaDTO create(@RequestBody CuotaExtraordinariaDTO dto) {
        return cuotaExtraordinariaService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        cuotaExtraordinariaService.deleteById(id);
    }
}
