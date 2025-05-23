package com.cramirez.backendcramirez.localizacion.infrastructure.web;
import com.cramirez.backendcramirez.localizacion.application.service.DepartamentoService;
import com.cramirez.backendcramirez.localizacion.domain.entity.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    // Obtener todos los departamentos
    @GetMapping
    public List<Departamento> obtenerTodos() {
        return departamentoService.obtenerTodos();
    }

    // Buscar un departamento por su nombre
    @GetMapping("/{nombre}")
    public Departamento obtenerPorNombre(@PathVariable String nombre) {
        return departamentoService.obtenerPorNombre(nombre);
    }
}
