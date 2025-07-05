package com.cramirez.backendcramirez.copropietario.application.service;

import com.cramirez.backendcramirez.copropietario.domain.entity.CopropietarioConyuge;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioConyugeDTO;
import com.cramirez.backendcramirez.copropietario.infrastructure.repository.CopropietarioConyugeRepository;
import com.cramirez.backendcramirez.documento.infrastructure.repository.IdentificacionRepository;

import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DepartamentoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ProvinciaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ResidenciaRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.NacionalidadRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.PrefijotelefonicoRepository;
import com.cramirez.backendcramirez.operario.infrastructure.repository.OperarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CopropietarioConyugeService {

    private final CopropietarioConyugeRepository copropietarioConyugeRepository;
    private final OperarioRepository operarioRepository;
    private final PrefijotelefonicoRepository prefijotelefonicoRepository;
    private final IdentificacionRepository identificacionRepository;
    private final NacionalidadRepository nacionalidadRepository;
    private final ResidenciaRepository residenciaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    @Autowired
    public CopropietarioConyugeService(CopropietarioConyugeRepository copropietarioConyugeRepository,OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
        this.copropietarioConyugeRepository = copropietarioConyugeRepository;
        this.operarioRepository = operarioRepository;
        this.prefijotelefonicoRepository = prefijotelefonicoRepository;
        this.identificacionRepository = identificacionRepository;
        this.nacionalidadRepository = nacionalidadRepository;
        this.residenciaRepository = residenciaRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
    }

    // Guardar un nuevo CopropietarioConyuge
    public CopropietarioConyugeDTO guardarCopropietarioConyuge(CopropietarioConyugeDTO dto) {
        CopropietarioConyuge entidad = convertirAEntidad(dto);
        CopropietarioConyuge guardado = copropietarioConyugeRepository.save(entidad);
        return convertirADTO(guardado);
    }

    // Obtener todos los CopropietarioConyuge
    public List<CopropietarioConyugeDTO> obtenerTodos() {
        return copropietarioConyugeRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener un CopropietarioConyuge por ID
    public CopropietarioConyugeDTO obtenerPorId(int id) {
        return copropietarioConyugeRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    // Actualizar un CopropietarioConyuge
    public CopropietarioConyugeDTO actualizarCopropietarioConyuge(int id, CopropietarioConyugeDTO dto) {
        return copropietarioConyugeRepository.findById(id)
                .map(entidad -> {
                    actualizarEntidad(entidad, dto);
                    CopropietarioConyuge actualizado = copropietarioConyugeRepository.save(entidad);
                    return convertirADTO(actualizado);
                })
                .orElse(null);
    }

    // Eliminar un CopropietarioConyuge
    public void eliminarCopropietarioConyuge(int id) {
        copropietarioConyugeRepository.deleteById(id);
    }

    // Conversión de Entidad a DTO
    private CopropietarioConyugeDTO convertirADTO(CopropietarioConyuge entidad) {
        CopropietarioConyugeDTO dto = new CopropietarioConyugeDTO();
        dto.setIdCopropietarioConyuge(entidad.getIdCopropietarioConyuge());
        dto.setIdClienteCopropietarioConyuge(entidad.getIdClienteCopropietarioConyuge());
        dto.setOcupacionCopropietarioConyuge(entidad.getOcupacionCopropietarioConyuge());
        dto.setIdResidenciaCopropietarioConyuge(entidad.getIdResidenciaCopropietarioConyuge());
        dto.setIdOperarioCopropietarioConyuge(entidad.getIdOperarioCopropietarioConyuge());
        dto.setIdDistritoCopropietarioConyuge(entidad.getIdDistritoCopropietarioConyuge());
        dto.setIdProvinciaCopropietarioConyuge(entidad.getIdProvinciaCopropietarioConyuge());
        dto.setIdDepartamentoCopropietarioConyuge(entidad.getIdDepartamentoCopropietarioConyuge());
        dto.setIdNacionalidadCopropietarioConyuge(entidad.getIdNacionalidadCopropietarioConyuge());
        dto.setIdIdentificacionCopropietarioConyuge(entidad.getIdIdentificacionCopropietarioConyuge());
        dto.setNombresApellidosCopropietarioConyuge(entidad.getNombresApellidosCopropietarioConyuge());
        dto.setDireccionCopropietarioConyuge(entidad.getDireccionCopropietarioConyuge());
        dto.setNumeroIdentificacionCopropietarioConyuge(entidad.getNumeroIdentificacionCopropietarioConyuge());

        dto.setOperarioCopropietarioConyuge(obtenerTexto(operarioRepository.findById(entidad.getIdOperarioCopropietarioConyuge()), "TipoOperario"));
        dto.setDocumentoIdentificacionCopropietarioConyuge(obtenerTexto(identificacionRepository.findById(entidad.getIdIdentificacionCopropietarioConyuge()), "DocumentoIdentificacion"));
        dto.setNacionalidadCopropietarioConyuge(obtenerTexto(nacionalidadRepository.findById(entidad.getIdNacionalidadCopropietarioConyuge()), "NombreNacionalidad"));
        dto.setResidenciaCopropietarioConyuge(obtenerTexto(residenciaRepository.findById(entidad.getIdResidenciaCopropietarioConyuge()), "Residencia"));
        dto.setDepartamentoCopropietarioConyuge(obtenerTexto(departamentoRepository.findById(entidad.getIdDepartamentoCopropietarioConyuge()), "NombreDepartamento"));
        dto.setProvinciaCopropietarioConyuge(obtenerTexto(provinciaRepository.findById(entidad.getIdProvinciaCopropietarioConyuge()), "NombreProvincia"));
        dto.setDistritoCopropietarioConyuge(obtenerTexto(distritoRepository.findById(entidad.getIdDistritoCopropietarioConyuge()), "NombreDistrito"));


        return dto;
    }

    private String obtenerTexto(Optional<?> entidad, String campo) {
        return entidad.map(e -> {
            try {
                return e.getClass().getMethod("get" + campo).invoke(e).toString();
            } catch (Exception ex) {
                return null;
            }
        }).orElse(null);
    }


    // Conversión de DTO a Entidad
    private CopropietarioConyuge convertirAEntidad(CopropietarioConyugeDTO dto) {
        CopropietarioConyuge entidad = new CopropietarioConyuge();
        actualizarEntidad(entidad, dto);
        return entidad;
    }

    // Actualizar la entidad desde el DTO
    private void actualizarEntidad(CopropietarioConyuge entidad, CopropietarioConyugeDTO dto) {
        entidad.setIdClienteCopropietarioConyuge(dto.getIdClienteCopropietarioConyuge());
        entidad.setOcupacionCopropietarioConyuge(dto.getOcupacionCopropietarioConyuge());
        entidad.setIdResidenciaCopropietarioConyuge(dto.getIdResidenciaCopropietarioConyuge());
        entidad.setIdOperarioCopropietarioConyuge(dto.getIdOperarioCopropietarioConyuge());
        entidad.setIdDistritoCopropietarioConyuge(dto.getIdDistritoCopropietarioConyuge());
        entidad.setIdProvinciaCopropietarioConyuge(dto.getIdProvinciaCopropietarioConyuge());
        entidad.setIdDepartamentoCopropietarioConyuge(dto.getIdDepartamentoCopropietarioConyuge());
        entidad.setIdNacionalidadCopropietarioConyuge(dto.getIdNacionalidadCopropietarioConyuge());
        entidad.setIdIdentificacionCopropietarioConyuge(dto.getIdIdentificacionCopropietarioConyuge());
        entidad.setNombresApellidosCopropietarioConyuge(dto.getNombresApellidosCopropietarioConyuge());
        entidad.setDireccionCopropietarioConyuge(dto.getDireccionCopropietarioConyuge());
        entidad.setNumeroIdentificacionCopropietarioConyuge(dto.getNumeroIdentificacionCopropietarioConyuge());
    }
}
