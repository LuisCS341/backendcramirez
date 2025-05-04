package com.cramirez.backendcramirez.localizacion.application.service;

import com.cramirez.backendcramirez.entity.localizacion.Departamento;
import com.cramirez.backendcramirez.repository.localizacion.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    // Obtener todos los departamentos
    public List<Departamento> obtenerTodos() {
        return departamentoRepository.findAll();
    }

    public Departamento obtenerPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento no puede ser nulo o vacío.");
        }

        return departamentoRepository.findByNombreDepartamento(nombre)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado: " + nombre));
    }

}
