    package com.cramirez.backendcramirez.auth.infrastructure.web;
    import com.cramirez.backendcramirez.auth.application.service.CredencialesService;
    import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
    import com.cramirez.backendcramirez.auth.jwt.JwtUtil;
    import com.cramirez.backendcramirez.auth.dto.CredencialesDTO;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.Map;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/auth")
    public class CredencialesController {

        private final JwtUtil jwtUtil;
        private final CredencialesService credencialesService;

        public CredencialesController(CredencialesService credencialesService, JwtUtil jwtUtil) {
            this.credencialesService = credencialesService;
            this.jwtUtil = jwtUtil;
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Credenciales credenciales) {
            System.out.println("Usuario recibido: " + credenciales.getUsuario());
            System.out.println("Contraseña recibida: " + credenciales.getContrasena());

            Optional<CredencialesDTO> credencialOpt = credencialesService.encontrarPorUsuario(credenciales.getUsuario());

            if (credencialOpt.isPresent()) {
                CredencialesDTO credencial = credencialOpt.get();
                if (credencial.getContrasena().equals(credenciales.getContrasena())) {

                    String token = jwtUtil.generarToken(credencial.getUsuario());

                    Map<String, Object> response = Map.of(
                            "token", token,
                            "rol", credencial.getIdTipoIdentidad(),
                            "nombre", credencial.getNombre(),
                            "idOperario", credencial.getIdOperario()
                    );
                    return ResponseEntity.ok(response);
                }
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "❌ Usuario o contraseña incorrectos."));
        }

    }
