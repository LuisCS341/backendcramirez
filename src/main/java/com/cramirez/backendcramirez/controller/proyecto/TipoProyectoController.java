package com.cramirez.backendcramirez.controller.proyecto;

import com.cramirez.backendcramirez.dto.proyecto.TipoProyectoDTO;
import com.cramirez.backendcramirez.service.proyecto.TipoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipoproyecto")
public class TipoProyectoController {

    @Autowired
    private TipoProyectoService tipoProyectoService;

    @GetMapping
    public List<TipoProyectoDTO> listarTodos() {
        return tipoProyectoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<TipoProyectoDTO> obtenerPorId(@PathVariable int id) {
        return tipoProyectoService.obtenerPorId(id);
    }

    @PostMapping
    public TipoProyectoDTO crear(@RequestBody TipoProyectoDTO tipoProyectoDTO) {
        return tipoProyectoService.guardar(tipoProyectoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        tipoProyectoService.eliminar(id);
    }
}
