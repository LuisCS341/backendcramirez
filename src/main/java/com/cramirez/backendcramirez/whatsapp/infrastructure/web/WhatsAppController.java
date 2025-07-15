package com.cramirez.backendcramirez.whatsapp.infrastructure.web;

import com.cramirez.backendcramirez.cliente.dto.ClienteDTO;
import com.cramirez.backendcramirez.whatsapp.application.service.WhatsAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/whatsapp")
public class WhatsAppController {

    private final WhatsAppService whatsAppService;

    public WhatsAppController(WhatsAppService whatsAppService) {
        this.whatsAppService = whatsAppService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarMensaje(@RequestBody ClienteDTO clienteDTO) {
        try {
            String celular = String.valueOf(clienteDTO.getCelularCliente());
            if (!celular.startsWith("+")) {
                celular = "+51" + celular;
            }

            String mensaje = "Hola " + clienteDTO.getNombresApellidos() + ", ¿llegó correctamente su minuta?";
            whatsAppService.enviarMensajeWhatsApp(celular, mensaje);

            return ResponseEntity.ok("Mensaje enviado correctamente a " + celular);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar mensaje: " + e.getMessage());
        }
    }

}
