package com.cramirez.backendcramirez.copropietario.application.service;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import com.cramirez.backendcramirez.copropietario.infrastructure.repository.CopropietarioRepository;
import com.cramirez.backendcramirez.documento.infrastructure.repository.IdentificacionRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DepartamentoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ProvinciaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ResidenciaRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.EstadoCivilRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.NacionalidadRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.PrefijotelefonicoRepository;
import com.cramirez.backendcramirez.operario.infrastructure.repository.OperarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CopropietarioService {

    private final CopropietarioRepository copropietarioRepository;
    private final OperarioRepository operarioRepository;
    private final PrefijotelefonicoRepository prefijotelefonicoRepository;
    private final IdentificacionRepository identificacionRepository;
    private final EstadoCivilRepository estadoCivilRepository;
    private final NacionalidadRepository nacionalidadRepository;
    private final ResidenciaRepository residenciaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    @Autowired
    public CopropietarioService(CopropietarioRepository copropietarioRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, EstadoCivilRepository estadoCivilRepository,NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
        this.copropietarioRepository = copropietarioRepository;
        this.operarioRepository = operarioRepository;
        this.prefijotelefonicoRepository = prefijotelefonicoRepository;
        this.identificacionRepository = identificacionRepository;
        this.estadoCivilRepository = estadoCivilRepository;
        this.nacionalidadRepository = nacionalidadRepository;
        this.residenciaRepository = residenciaRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
    }

    public List<CopropietarioDTO> obtenerTodosLosCopropietarios() {
        List<Copropietario> copropietarios = copropietarioRepository.findAll();
        return copropietarios.stream().map(this::convertirACopropietarioDTO).collect(Collectors.toList());
    }

    public CopropietarioDTO obtenerCopropietarioPorId(int id) {
        return copropietarioRepository.findById(id)
                .map(this::convertirACopropietarioDTO)
                .orElse(null);
    }

    public CopropietarioDTO crearCopropietario(CopropietarioDTO copropietarioDTO) {
        Copropietario copropietario = convertirACopropietarioEntidad(copropietarioDTO);
        Copropietario copropietarioGuardado = copropietarioRepository.save(copropietario);
        return convertirACopropietarioDTO(copropietarioGuardado);
    }

    public CopropietarioDTO actualizarCopropietario(int id, CopropietarioDTO copropietarioDTO) {
        return copropietarioRepository.findById(id)
                .map(copropietario -> {
                    copropietario.setIdClienteCopropietarios(copropietarioDTO.getIdClienteCopropietarios());
                    copropietario.setIdResidenciaCopropietarios(copropietarioDTO.getIdResidenciaCopropietarios());
                    copropietario.setIdPrefijoCopropietarios(copropietarioDTO.getIdPrefijoCopropietarios());
                    copropietario.setIdOperarioCopropietarios(copropietarioDTO.getIdOperarioCopropietarios());
                    copropietario.setOcupacionCopropietarios(copropietarioDTO.getOcupacionCopropietarios());
                    copropietario.setIdDepartamentoCopropietarios(copropietarioDTO.getIdDepartamentoCopropietarios());
                    copropietario.setIdProvinciaCopropietarios(copropietarioDTO.getIdProvinciaCopropietarios());
                    copropietario.setIdDistritoCopropietarios(copropietarioDTO.getIdDistritoCopropietarios());
                    copropietario.setIdNacionalidadCopropietarios(copropietarioDTO.getIdNacionalidadCopropietarios());
                    copropietario.setIdEstadoCivilCopropietarios(copropietarioDTO.getIdEstadoCivilCopropietarios());
                    copropietario.setIdIdentificacionCopropietarios(copropietarioDTO.getIdIdentificacionCopropietarios());
                    copropietario.setNombresApellidosCopropietarios(copropietarioDTO.getNombresApellidosCopropietarios());
                    copropietario.setDireccionCopropietarios(copropietarioDTO.getDireccionCopropietarios());
                    copropietario.setCorreoElectronicoCopropietarios(copropietarioDTO.getCorreoElectronicoCopropietarios());
                    copropietario.setCelularCopropietarios(copropietarioDTO.getCelularCopropietarios());
                    copropietario.setNumeroIdentificacionCopropietarios(copropietarioDTO.getNumeroIdentificacionCopropietarios());

                    Copropietario copropietarioActualizado = copropietarioRepository.save(copropietario);
                    return convertirACopropietarioDTO(copropietarioActualizado);
                })
                .orElse(null);
    }

    public void eliminarCopropietario(int id) {
        copropietarioRepository.deleteById(id);
    }

    private CopropietarioDTO convertirACopropietarioDTO(Copropietario copropietario) {
        CopropietarioDTO dto = new CopropietarioDTO();
        dto.setIdCopropietario(copropietario.getIdCopropietario());
        dto.setIdClienteCopropietarios(copropietario.getIdClienteCopropietarios());
        dto.setIdResidenciaCopropietarios(copropietario.getIdResidenciaCopropietarios());
        dto.setIdPrefijoCopropietarios(copropietario.getIdPrefijoCopropietarios());
        dto.setIdOperarioCopropietarios(copropietario.getIdOperarioCopropietarios());
        dto.setOcupacionCopropietarios(copropietario.getOcupacionCopropietarios());
        dto.setIdDepartamentoCopropietarios(copropietario.getIdDepartamentoCopropietarios());
        dto.setIdProvinciaCopropietarios(copropietario.getIdProvinciaCopropietarios());
        dto.setIdDistritoCopropietarios(copropietario.getIdDistritoCopropietarios());
        dto.setIdNacionalidadCopropietarios(copropietario.getIdNacionalidadCopropietarios());
        dto.setIdEstadoCivilCopropietarios(copropietario.getIdEstadoCivilCopropietarios());
        dto.setIdIdentificacionCopropietarios(copropietario.getIdIdentificacionCopropietarios());
        dto.setNombresApellidosCopropietarios(copropietario.getNombresApellidosCopropietarios());
        dto.setDireccionCopropietarios(copropietario.getDireccionCopropietarios());
        dto.setCorreoElectronicoCopropietarios(copropietario.getCorreoElectronicoCopropietarios());
        dto.setCelularCopropietarios(copropietario.getCelularCopropietarios());
        dto.setNumeroIdentificacionCopropietarios(copropietario.getNumeroIdentificacionCopropietarios());
        dto.setDescripcionEstadoCivilCopropietarios(copropietario.getDescripcionEstadoCivilCopropietarios());

        dto.setOperarioCopropietarios(obtenerTexto(operarioRepository.findById(copropietario.getIdOperarioCopropietarios()), "TipoOperario"));
        dto.setPrefijoPaisCopropietarios(obtenerTexto(prefijotelefonicoRepository.findById(copropietario.getIdPrefijoCopropietarios()), "PrefijoPais"));
        dto.setDocumentoIdentificacionCopropietarios(obtenerTexto(identificacionRepository.findById(copropietario.getIdIdentificacionCopropietarios()), "DocumentoIdentificacion"));
        dto.setEstadoCivilCopropietarios(obtenerTexto(estadoCivilRepository.findById(copropietario.getIdEstadoCivilCopropietarios()), "EstadoCivil"));
        dto.setNacionalidadCopropietarios(obtenerTexto(nacionalidadRepository.findById(copropietario.getIdNacionalidadCopropietarios()), "NombreNacionalidad"));
        dto.setResidenciaCopropietarios(obtenerTexto(residenciaRepository.findById(copropietario.getIdResidenciaCopropietarios()), "Residencia"));
        dto.setDepartamentoCopropietarios(obtenerTexto(departamentoRepository.findById(copropietario.getIdDepartamentoCopropietarios()), "NombreDepartamento"));
        dto.setProvinciaCopropietarios(obtenerTexto(provinciaRepository.findById(copropietario.getIdProvinciaCopropietarios()), "NombreProvincia"));
        dto.setDistritoCopropietarios(obtenerTexto(distritoRepository.findById(copropietario.getIdDistritoCopropietarios()), "NombreDistrito"));

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

    private Copropietario convertirACopropietarioEntidad(CopropietarioDTO dto) {
        Copropietario copropietario = new Copropietario();
        copropietario.setIdClienteCopropietarios(dto.getIdClienteCopropietarios());
        copropietario.setIdResidenciaCopropietarios(dto.getIdResidenciaCopropietarios());
        copropietario.setIdPrefijoCopropietarios(dto.getIdPrefijoCopropietarios());
        copropietario.setOcupacionCopropietarios(dto.getOcupacionCopropietarios());
        copropietario.setIdOperarioCopropietarios(dto.getIdOperarioCopropietarios());
        copropietario.setIdDepartamentoCopropietarios(dto.getIdDepartamentoCopropietarios());
        copropietario.setIdProvinciaCopropietarios(dto.getIdProvinciaCopropietarios());
        copropietario.setIdDistritoCopropietarios(dto.getIdDistritoCopropietarios());
        copropietario.setIdNacionalidadCopropietarios(dto.getIdNacionalidadCopropietarios());
        copropietario.setIdEstadoCivilCopropietarios(dto.getIdEstadoCivilCopropietarios());
        copropietario.setIdIdentificacionCopropietarios(dto.getIdIdentificacionCopropietarios());
        copropietario.setNombresApellidosCopropietarios(dto.getNombresApellidosCopropietarios());
        copropietario.setDireccionCopropietarios(dto.getDireccionCopropietarios());
        copropietario.setCorreoElectronicoCopropietarios(dto.getCorreoElectronicoCopropietarios());
        copropietario.setCelularCopropietarios(dto.getCelularCopropietarios());
        copropietario.setNumeroIdentificacionCopropietarios(dto.getNumeroIdentificacionCopropietarios());
        copropietario.setDescripcionEstadoCivilCopropietarios(dto.getDescripcionEstadoCivilCopropietarios());
        return copropietario;
    }
}
