package com.cramirez.backendcramirez.cliente.infrastructure.web;
import com.cramirez.backendcramirez.cliente.dto.ClienteConyugeDTO;
import com.cramirez.backendcramirez.cliente.application.service.ClienteConyugeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clienteConyuges")
public class ClienteConyugeController {

    private final ClienteConyugeService clienteConyugeService;

    @Autowired
    public ClienteConyugeController(ClienteConyugeService clienteConyugeService) {
        this.clienteConyugeService = clienteConyugeService;
    }

    @PostMapping
    public ResponseEntity<?> guardarClienteConyuge(
            @RequestBody ClienteConyugeDTO clienteConyugeDTO,
            @RequestHeader("X-User-ID") Integer idOperarioConyuge) {

        if (idOperarioConyuge == null) {
            return ResponseEntity.badRequest().body("ID_Operario es requerido en el encabezado X-User-ID");
        }

        clienteConyugeDTO.setIdOperarioConyuge(idOperarioConyuge);

        ClienteConyugeDTO conyugeGuardado = clienteConyugeService.guardarClienteConyuge(clienteConyugeDTO);

        return ResponseEntity.ok(conyugeGuardado);
    }

    // Obtener todos los ClienteConyuges
    @GetMapping
    public List<ClienteConyugeDTO> obtenerTodosLosClienteConyuges() {
        return clienteConyugeService.obtenerTodosLosClienteConyuges();
    }

    // Obtener un ClienteConyuge por ID
    @GetMapping("/{id}")
    public ClienteConyugeDTO obtenerClienteConyugePorId(@PathVariable int id) {
        return clienteConyugeService.obtenerClienteConyugePorId(id);
    }

    // Actualizar un ClienteConyuge existente
    @PutMapping("/{id}")
    public ClienteConyugeDTO actualizarClienteConyuge(
            @PathVariable int id,
            @RequestBody ClienteConyugeDTO clienteConyugeDTO) {
        return clienteConyugeService.actualizarClienteConyuge(id, clienteConyugeDTO);
    }

    // Eliminar un ClienteConyuge
    @DeleteMapping("/{id}")
    public void eliminarClienteConyuge(@PathVariable int id) {
        clienteConyugeService.eliminarClienteConyuge(id);
    }
}
