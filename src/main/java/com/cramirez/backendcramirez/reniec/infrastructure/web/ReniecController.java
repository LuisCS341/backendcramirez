package com.cramirez.backendcramirez.reniec.infrastructure.web;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ReniecController {

    private static final String API_URL = "https://api.apis.net.pe/v2/reniec/dni?numero=";
    private static final String AUTHORIZATION_TOKEN = "apis-token-13743.JAxDGOGDqgM1YDvLgoWtbtQBhmbNWvFr";

    @GetMapping("/buscarCliente/{dni}")
    public ResponseEntity<String> buscarCliente(@PathVariable String dni) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // Configurar cabeceras HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTHORIZATION_TOKEN);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Realizar solicitud a la API externa
            ResponseEntity<String> response = restTemplate.exchange(
                    API_URL + dni,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Error al consultar RENIEC");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al consultar RENIEC: " + e.getMessage());
        }
    }

    @GetMapping("/buscarEmpresa/{ruc}")
    public ResponseEntity<String> buscarEmpresa(@PathVariable String ruc) {
        final String API_SUNAT_URL = "https://api.apis.net.pe/v2/sunat/ruc/full?numero=";

        try {
            RestTemplate restTemplate = new RestTemplate();

            // Configurar cabeceras HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", AUTHORIZATION_TOKEN);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Realizar solicitud a la API externa
            ResponseEntity<String> response = restTemplate.exchange(
                    API_SUNAT_URL + ruc,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Error al consultar SUNAT");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al consultar SUNAT: " + e.getMessage());
        }
    }

}
