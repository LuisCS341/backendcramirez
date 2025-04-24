package com.cramirez.backendcramirez.controller.localizacion;

import com.cramirez.backendcramirez.entity.localizacion.Departamento;
import com.cramirez.backendcramirez.service.localizacion.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
