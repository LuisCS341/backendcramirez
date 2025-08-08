package com.cramirez.backendcramirez.cliente.infrastructure.web;
import com.cramirez.backendcramirez.cliente.dto.ClienteDTO;
import com.cramirez.backendcramirez.cliente.application.service.ClienteService;
import com.cramirez.backendcramirez.cliente.dto.LoteConClienteCompletoDTO;
import com.cramirez.backendcramirez.cliente.dto.TransferenciaClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/registrados/count")
    public ResponseEntity<Integer> contarClientesRegistrados() {
        int total = clienteService.contarClientesRegistrados();
        return ResponseEntity.ok(total);
    }
    @GetMapping("/registrados-mes-tipo-operario")
    public ResponseEntity<List<Map<String, Object>>> registradosPorTipoOperario() {
        return ResponseEntity.ok(
                clienteService.contarClientesRegistradosPorTipoOperarioDelMes()
        );
    }

    @GetMapping("/total-registrados")
    public int obtenerTotalClientesRegistrados() {
        return clienteService.TotalClientesRegistrados();
    }


    /*
    @GetMapping("/tiempo-promedio-cliente")
    public ResponseEntity<Integer> obtenerTiempoPromedioCliente() {
        int tiempo = clienteService.obtenerTiempoPromedioPorCliente();
        return ResponseEntity.ok(tiempo);
    }

     */


    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientes();
       return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteConClienteCompletoDTO> obtenerClienteConLotesPorId(@PathVariable int id) {
        LoteConClienteCompletoDTO clienteConLotes = clienteService.obtenerClientePorId(id);
        return clienteConLotes != null ? ResponseEntity.ok(clienteConLotes) : ResponseEntity.notFound().build();
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
    public List<LoteConClienteCompletoDTO> obtenerClientesConLotes() {
        return clienteService.obtenerClientesConLotes();
    }


    @PutMapping("/transferir/{idCliente}")
    public ResponseEntity<String> transferirCliente(
            @PathVariable int idCliente,
            @RequestBody TransferenciaClienteDTO dto) {

        try {
            clienteService.transferirCliente(idCliente, dto);

            return ResponseEntity.ok("Cliente transferido correctamente.");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/editar/{idCliente}")
    public ResponseEntity<LoteConClienteCompletoDTO> editarClienteYComponentes(
            @PathVariable int idCliente,
            @RequestBody LoteConClienteCompletoDTO loteConClienteCompletoDTO) {

        if (loteConClienteCompletoDTO.getCliente() == null ||
                loteConClienteCompletoDTO.getCliente().getIdCliente() != idCliente) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            LoteConClienteCompletoDTO loteConClienteCompletoDTOResult =
                    clienteService.editarClienteYComponentes(loteConClienteCompletoDTO);
            return ResponseEntity.ok(loteConClienteCompletoDTOResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @GetMapping("/por-operario/{idOperario}")
    public ResponseEntity<List<LoteConClienteCompletoDTO>> obtenerClientesPorOperario(@PathVariable int idOperario) {
        List<LoteConClienteCompletoDTO> clientes = clienteService.obtenerClientesConLotesPorOperario(idOperario);
        return ResponseEntity.ok(clientes);
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


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable int id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}