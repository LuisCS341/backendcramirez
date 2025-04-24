package com.cramirez.backendcramirez.controller.auth;

import com.cramirez.backendcramirez.entity.auth.Credenciales;
import com.cramirez.backendcramirez.service.auth.CredencialesService;
import com.cramirez.backendcramirez.service.email.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Optional<Credenciales> credencial = credencialesService.encontrarPorUsuario(usuario);

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
            Optional<Credenciales> credencial = credencialesService.encontrarPorUsuario(usuario);

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
