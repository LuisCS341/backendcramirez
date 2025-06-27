package com.cramirez.backendcramirez.lote.infrastructure.web;

import com.cramirez.backendcramirez.lote.application.service.CuotaService;
import com.cramirez.backendcramirez.lote.dto.CuotaDTO;
import com.cramirez.backendcramirez.lote.domain.entity.Cuota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuota")
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;

    @PostMapping
    public ResponseEntity<Cuota> guardarCuota(@RequestBody CuotaDTO cuotaDTO) {
        Cuota cuotaGuardada = cuotaService.convertToEntity(cuotaDTO);
        return ResponseEntity.ok(cuotaGuardada);
    }

    @GetMapping
    public List<CuotaDTO> listarCuotas() {
        return cuotaService.convertirACuotaDTO();
    }
}
