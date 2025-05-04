package com.cramirez.backendcramirez.cita.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Cita")
@Getter
@Setter
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cita", nullable = false)
    private int idCita;

    @Column(name = "ID_Cliente", nullable = false)
    private int idCliente;

    @Column(name = "Fecha_Cita", nullable = true)
    private String fechaCita;

    @Column(name = "Hora_Cita", nullable = true)
    private String horaCita;

    @Column(name = "Persona_Atiende", nullable = true)
    private String personaAtiende;

    // Constructor vacío
    public Cita() {
    }

    // Constructor con parámetros
    public Cita(int idCita, int idCliente, String fechaCita, String horaCita, String personaAtiende) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.personaAtiende = personaAtiende;
    }

    // Getters y Setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public String getPersonaAtiende() {
        return personaAtiende;
    }

    public void setPersonaAtiende(String personaAtiende) {
        this.personaAtiende = personaAtiende;
    }
}
