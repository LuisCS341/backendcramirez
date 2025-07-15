package com.cramirez.backendcramirez.emailnotificacion.infrastructure.web;

import com.cramirez.backendcramirez.emailnotificacion.application.service.EmailNotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailNotificacionController {

    private final EmailNotificacionService emailService;

    @Autowired
    public EmailNotificacionController(EmailNotificacionService emailService) {
        this.emailService = emailService;
    }


    @PostMapping("/enviar-cliente/{idCliente}")
    public ResponseEntity<String> enviarCorreoCliente(@PathVariable Integer idCliente) {
        emailService.sendEmail(idCliente);
        return ResponseEntity.ok("Correo enviado al cliente con ID: " + idCliente);
    }
}
