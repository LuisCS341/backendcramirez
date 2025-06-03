package com.cramirez.backendcramirez.auth.infrastructure.web;
import com.cramirez.backendcramirez.auth.application.service.CredencialesService;
import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
import com.cramirez.backendcramirez.auth.dto.CredencialesDTO;
import com.cramirez.backendcramirez.email.application.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/authEmail")
public class AuthEmailController {

    private final CredencialesService credencialesService;
    private final EmailService emailService;
    private final Map<String, String> verificationCodes = new HashMap<>();

    public AuthEmailController(CredencialesService credencialesService, EmailService emailService) {
        this.credencialesService = credencialesService;
        this.emailService = emailService;
    }

    // 1️⃣ Enviar código de verificación
    @PostMapping("/send-code")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String usuario, @RequestParam String email) {
        Optional<CredencialesDTO> credencial = credencialesService.encontrarPorUsuario(usuario);

        if (credencial.isPresent() && credencial.get().getEmailUsuario().equals(email)) {
            String code = String.valueOf(new Random().nextInt(900000) + 100000); // Código de 6 dígitos
            verificationCodes.put(usuario, code); // Guardar código en memoria (temporalmente)
            emailService.sendEmail(email, "Código de verificación", "Tu código es: " + code);
            return ResponseEntity.ok("Código enviado con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o email incorrecto.");
        }
    }

    // 2️⃣ Validar código y cambiar contraseña
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String usuario, @RequestParam String code, @RequestParam String newPassword) {
        if (verificationCodes.containsKey(usuario) && verificationCodes.get(usuario).equals(code)) {
            Optional<CredencialesDTO> credencial = credencialesService.encontrarPorUsuario(usuario);

            if (credencial.isPresent()) {
                credencial.get().setContrasena(newPassword); // Se debería encriptar la contraseña
                credencialesService.actualizarCredenciales(credencial.get());
                verificationCodes.remove(usuario); // Eliminar código después del uso

                return "Contraseña cambiada exitosamente.";
            }
        }
        return "Código incorrecto o expirado.";
    }
}
