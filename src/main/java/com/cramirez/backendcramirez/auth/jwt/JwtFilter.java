package com.cramirez.backendcramirez.auth.jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ⛔ Rutas públicas (ignorar token completamente)
        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Sólo validar token para rutas protegidas
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Falta el token JWT");
            return;
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validarToken(token)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Token JWT inválido");
            return;
        }

        String usuario = jwtUtil.extraerUsuario(token);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/api/auth") ||
                path.startsWith("/api/buscarCliente") ||
                path.equals("/api/clientes/existe") ||
                path.equals("/api/clientes/buscar") ||
                path.startsWith("/api/clientes/") ||
                path.startsWith("/api/clienteConyuges") ||
                path.startsWith("/api/clientes/conlotes") ||
                path.startsWith("/api/copropietario") ||
                path.startsWith("/api/copropietarioconyuge") ||
                path.startsWith("/api/matrices") ||
                path.startsWith("/api/cuotaextraordinaria") ||
                path.startsWith("/api/lindero") ||
                path.startsWith("/api/clientes/por-operario") ||
                path.startsWith("/api/lotes") ||
                path.equals("/api/authEmail/send-code") ||
                path.equals("/api/authEmail/reset-password");
    }
}