package com.cramirez.backendcramirez.cliente.infrastructure.web;

import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.dto.ClienteConLotesDTO;
import com.cramirez.backendcramirez.cliente.dto.ClienteDTO;
import com.cramirez.backendcramirez.cliente.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Boolean> verificarExistenciaPorNumeroIdentificacion(@RequestParam("numeroIdentificacion") String numeroIdentificacion) {
        boolean existe = clienteService.existeClientePorNumeroIdentificacion(numeroIdentificacion);
        return ResponseEntity.ok(existe);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ClienteDTO> obtenerClientePorNumeroIdentificacion(
            @RequestParam("numeroIdentificacion") String numeroIdentificacion) {
        Optional<ClienteDTO> clienteDTO = clienteService.obtenerClienteDTOporNumeroIdentificacion(numeroIdentificacion);
        return clienteDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @GetMapping("/conlotes")
    public List<ClienteConLotesDTO> obtenerClientesConLotes() {
        return clienteService.obtenerClientesConLotes();
    }

    @GetMapping("/por-operario/{idOperario}")
    public ResponseEntity<List<ClienteConLotesDTO>> obtenerClientesPorOperario(@PathVariable int idOperario) {
        List<ClienteConLotesDTO> clientes = clienteService.obtenerClientesConLotesPorOperario(idOperario);
        return ResponseEntity.ok(clientes); // 200 con la lista
    }


    @PostMapping
    public ResponseEntity<?> asignarOperario(
            @RequestBody ClienteDTO clienteDTO,
            @RequestHeader("X-User-ID") Integer idOperario) {

        if (idOperario == null) {
            return ResponseEntity.badRequest().body("ID_Operario es requerido en el encabezado X-User-ID");
        }

        clienteDTO.setIdOperario(idOperario);

        ClienteDTO clienteGuardado = clienteService.guardarCliente(clienteDTO);

        return ResponseEntity.ok(clienteGuardado);
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