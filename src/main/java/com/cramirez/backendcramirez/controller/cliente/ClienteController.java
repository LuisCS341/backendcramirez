package com.cramirez.backendcramirez.controller.cliente;

import com.cramirez.backendcramirez.dto.cliente.ClienteDTO;
import com.cramirez.backendcramirez.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable int id) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(id);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> verificarExistenciaPorNumeroIdentificacion(
            @RequestParam("numeroIdentificacion") String numeroIdentificacion) {

        boolean existe = clienteService.existeClientePorNumeroIdentificacion(numeroIdentificacion);
        return ResponseEntity.ok(existe);
    }


    @PostMapping
    public ResponseEntity<?> asignarOperario(
            @RequestBody ClienteDTO clienteDTO,
            @RequestHeader("X-User-ID") Integer idOperario) {

        if (idOperario == null) {
            return ResponseEntity.badRequest().body("ID_Operario es requerido en el encabezado X-User-ID");
        }

        clienteDTO.setIdOperario(idOperario);

        clienteService.guardarCliente(clienteDTO);

        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/operario")
    public List<ClienteDTO> obtenerClientesPorOperario(@RequestHeader("X-User-ID") int idOperario) {
        System.out.println("ID Operario recibido: " + idOperario);
        return clienteService.obtenerClientesPorOperario(idOperario);
    }

    @GetMapping("/ultimo")
    public ResponseEntity<Integer> obtenerUltimoIdCliente() {
        Integer ultimoId = clienteService.obtenerUltimoIdCliente();
        return ResponseEntity.ok(ultimoId);
    }

    @GetMapping("/clientes/operario/{idOperario}/fecha/{fecha}")
    public ResponseEntity<List<ClienteDTO>> obtenerClientesPorOperarioYFecha(
            @PathVariable int idOperario,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<ClienteDTO> clientes = clienteService.obtenerClientesPorOperarioYFecha(idOperario, fecha);
        return ResponseEntity.ok(clientes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable int id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);
        return clienteActualizado != null ? ResponseEntity.ok(clienteActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}