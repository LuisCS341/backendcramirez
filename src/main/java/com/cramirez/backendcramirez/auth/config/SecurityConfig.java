package com.cramirez.backendcramirez.auth.config;

import com.cramirez.backendcramirez.auth.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/buscarCliente/**",
                                "/api/clientes/existe",
                                "/api/clientes/buscar",
                                "/api/clientes/**",
                                "/api/clienteConyuges/**",
                                "/api/clientes/conlotes",
                                "/api/copropietario/**",
                                "/api/copropietarioconyuge/**",
                                "/api/matrices/**",
                                "/api/cuotaextraordinaria/**",
                                "/api/lindero/**",
                                "/api/clientes/por-operario/**",
                                "/api/lotes/**",
                                "/api/authEmail/send-code",
                                "/api/authEmail/reset-password"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/api/buscarCliente/**",
                                "/api/clientes/existe",
                                "/api/clientes/buscar",
                                "/api/clientes/**",
                                "/api/clienteConyuges/**",
                                "/api/clientes/conlotes",
                                "/api/copropietario/**",
                                "/api/copropietarioconyuge/**",
                                "/api/matrices/**",
                                "/api/cuotaextraordinaria/**",
                                "/api/lindero/**",
                                "/api/lotes/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
