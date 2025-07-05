package com.cramirez.backendcramirez.cliente.application.service;

import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import com.cramirez.backendcramirez.cliente.dto.ClienteConyugeDTO;
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
public class ClienteConyugeService {

    private final com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteConyugeRepository clienteConyugeRepository;
    private final OperarioRepository operarioRepository;
    private final PrefijotelefonicoRepository prefijotelefonicoRepository;
    private final IdentificacionRepository identificacionRepository;
    private final NacionalidadRepository nacionalidadRepository;
    private final ResidenciaRepository residenciaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    @Autowired
    public ClienteConyugeService(com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteConyugeRepository clienteConyugeRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
        this.clienteConyugeRepository = clienteConyugeRepository;
        this.operarioRepository = operarioRepository;
        this.prefijotelefonicoRepository = prefijotelefonicoRepository;
        this.identificacionRepository = identificacionRepository;
        this.nacionalidadRepository = nacionalidadRepository;
        this.residenciaRepository = residenciaRepository;
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
    }

    public ClienteConyugeDTO guardarClienteConyuge(ClienteConyugeDTO clienteConyugeDTO) {
        // Verificar si ya existe un conyuge para el cliente
        Optional<ClienteConyuge> clienteConyugeExistente = clienteConyugeRepository.findByIdCliente(clienteConyugeDTO.getIdCliente());

        if (clienteConyugeExistente.isPresent()) {

            return convertirAClienteConyugeDTO(clienteConyugeExistente.get());
        }

        ClienteConyuge clienteConyuge = convertirAClienteConyugeEntidad(clienteConyugeDTO);
        ClienteConyuge clienteConyugeGuardado = clienteConyugeRepository.save(clienteConyuge);

        return convertirAClienteConyugeDTO(clienteConyugeGuardado);
    }


    // Obtener todos los ClienteConyuge
    public List<ClienteConyugeDTO> obtenerTodosLosClienteConyuges() {
        return clienteConyugeRepository.findAll()
                .stream()
                .map(this::convertirAClienteConyugeDTO)
                .collect(Collectors.toList());
    }

    // Obtener ClienteConyuge por ID
    public ClienteConyugeDTO obtenerClienteConyugePorId(int id) {
        return clienteConyugeRepository.findById(id)
                .map(this::convertirAClienteConyugeDTO)
                .orElse(null);
    }

    // Actualizar ClienteConyuge
    public ClienteConyugeDTO actualizarClienteConyuge(int id, ClienteConyugeDTO clienteConyugeDTO) {
        return clienteConyugeRepository.findById(id)
                .map(clienteConyuge -> {
                    actualizarEntidad(clienteConyuge, clienteConyugeDTO);
                    return convertirAClienteConyugeDTO(clienteConyugeRepository.save(clienteConyuge));
                })
                .orElse(null);
    }

    // Eliminar ClienteConyuge
    public void eliminarClienteConyuge(int id) {
        clienteConyugeRepository.deleteById(id);
    }


    private ClienteConyugeDTO convertirAClienteConyugeDTO(ClienteConyuge clienteConyuge) {
        ClienteConyugeDTO dto = new ClienteConyugeDTO();
        dto.setIdClienteConyuge(clienteConyuge.getIdClienteConyuge());
        dto.setIdCliente(clienteConyuge.getIdCliente());
        dto.setIdNacionalidadConyuge(clienteConyuge.getIdNacionalidadConyuge());
        dto.setOcupacionConyuge(clienteConyuge.getOcupacionConyuge());
        dto.setIdIdentificacionConyuge(clienteConyuge.getIdIdentificacionConyuge());
        dto.setIdResidenciaConyuge(clienteConyuge.getIdResidenciaConyuge());
        dto.setIdOperarioConyuge(clienteConyuge.getIdOperarioConyuge());
        dto.setIdDepartamentoConyuge(clienteConyuge.getIdDepartamentoConyuge());
        dto.setIdProvinciaConyuge(clienteConyuge.getIdProvinciaConyuge());
        dto.setIdDistritoConyuge(clienteConyuge.getIdDistritoConyuge());
        dto.setNombresApellidosConyuge(clienteConyuge.getNombresApellidosConyuge());
        dto.setDireccionConyuge(clienteConyuge.getDireccionConyuge());
        dto.setNumeroIdentificacionConyuge(clienteConyuge.getNumeroIdentificacionConyuge());


        dto.setOperarioConyuge(obtenerTexto(operarioRepository.findById(clienteConyuge.getIdOperarioConyuge()), "TipoOperario"));
        dto.setDocumentoIdentificacionConyuge(obtenerTexto(identificacionRepository.findById(clienteConyuge.getIdIdentificacionConyuge()), "DocumentoIdentificacion"));
        dto.setNacionalidadConyuge(obtenerTexto(nacionalidadRepository.findById(clienteConyuge.getIdNacionalidadConyuge()), "NombreNacionalidad"));
        dto.setResidenciaConyuge(obtenerTexto(residenciaRepository.findById(clienteConyuge.getIdResidenciaConyuge()), "Residencia"));
        dto.setDepartamentoConyuge(obtenerTexto(departamentoRepository.findById(clienteConyuge.getIdDepartamentoConyuge()), "NombreDepartamento"));
        dto.setProvinciaConyuge(obtenerTexto(provinciaRepository.findById(clienteConyuge.getIdProvinciaConyuge()), "NombreProvincia"));
        dto.setDistritoConyuge(obtenerTexto(distritoRepository.findById(clienteConyuge.getIdDistritoConyuge()), "NombreDistrito"));


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


    // Conversión de ClienteConyugeDTO a ClienteConyuge
    private ClienteConyuge convertirAClienteConyugeEntidad(ClienteConyugeDTO dto) {
        ClienteConyuge clienteConyuge = new ClienteConyuge();
        actualizarEntidad(clienteConyuge, dto);
        return clienteConyuge;
    }

    // Actualización de ClienteConyuge desde DTO
    private void actualizarEntidad(ClienteConyuge clienteConyuge, ClienteConyugeDTO dto) {
        clienteConyuge.setIdCliente(dto.getIdCliente());
        clienteConyuge.setIdClienteConyuge(dto.getIdClienteConyuge());
        clienteConyuge.setIdNacionalidadConyuge(dto.getIdNacionalidadConyuge());
        clienteConyuge.setOcupacionConyuge(dto.getOcupacionConyuge());
        clienteConyuge.setIdIdentificacionConyuge(dto.getIdIdentificacionConyuge());
        clienteConyuge.setIdResidenciaConyuge(dto.getIdResidenciaConyuge());
        clienteConyuge.setIdOperarioConyuge(dto.getIdOperarioConyuge());
        clienteConyuge.setIdDepartamentoConyuge(dto.getIdDepartamentoConyuge());
        clienteConyuge.setIdProvinciaConyuge(dto.getIdProvinciaConyuge());
        clienteConyuge.setIdDistritoConyuge(dto.getIdDistritoConyuge());
        clienteConyuge.setNombresApellidosConyuge(dto.getNombresApellidosConyuge());
        clienteConyuge.setDireccionConyuge(dto.getDireccionConyuge());
        clienteConyuge.setNumeroIdentificacionConyuge(dto.getNumeroIdentificacionConyuge());
    }
}
