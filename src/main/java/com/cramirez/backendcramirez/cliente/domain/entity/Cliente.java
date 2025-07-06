package com.cramirez.backendcramirez.cliente.domain.entity;

import com.cramirez.backendcramirez.lote.domain.entity.Lote;
import com.cramirez.backendcramirez.operario.domain.entity.Operario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Cliente")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente",nullable = false)
    private int idCliente;

    @Column(name = "ID_ClienteClone",nullable = false)
    private int idClienteClone;

    @Column(name = "ID_Operario")
    private Integer  idOperario;

    @ManyToOne
    @JoinColumn(name = "ID_Operario", referencedColumnName = "ID_Operario", insertable = false, updatable = false)
    private Operario operario;


    @Column(name = "ID_Identificacion",nullable = false)
    private int idIdentificacion;

    @Column(name = "ID_EstadoCivil",nullable = false)
    private int idEstadoCivil;

    @Column(name = "ID_Nacionalidad",nullable = false)
    private int idNacionalidad;

    @Column(name = "ID_Residencia",nullable = false)
    private int idResidencia;

    @Column(name = "NombresApellidosCliente",nullable = false)
    private String nombresApellidos;

    @Column(name = "OcupacionCliente",nullable = false)
    private String ocupacion;

    @Column(name = "DireccionCliente",nullable = false)
    private String direccion;

    @Column(name = "CorreoElectronicoCliente",nullable = false)
    private String correoElectronico;

    @Column(name = "DescripcionEstadoCivilCliente",nullable = false)
    private String descripcionEstadoCivil;

    @Column(name = "CelularCliente",nullable = false)
    private int celularCliente;

    @Column(name = "NumeroIdentificacionCliente",nullable = false,unique = true)
    private String numeroIdentificacion;

    @Column(name = "ID_Departamento")
    private int idDepartamento;

    @Column(name = "ID_Provincia")
    private int idProvincia;

    @Column(name = "ID_Distrito")
    private int idDistrito;

    @Column(name = "ID_Prefijo",nullable = false)
    private int idPrefijo;

    @Column(name = "FechaRegistroCliente", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "cliente")
    private List<Lote> lotes;

}
