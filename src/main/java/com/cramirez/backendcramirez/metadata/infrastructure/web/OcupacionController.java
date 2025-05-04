package com.cramirez.backendcramirez.metadata.infrastructure.web;

import com.cramirez.backendcramirez.dto.metadata.OcupacionDTO;
import com.cramirez.backendcramirez.service.metadata.OcupacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ocupaciones")
public class OcupacionController {

    @Autowired
    private OcupacionService ocupacionService;

    @GetMapping("/listar")
    public List<OcupacionDTO> listarOcupaciones() {
        return ocupacionService.listarOcupaciones();
    }
}