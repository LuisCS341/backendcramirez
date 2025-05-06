package com.cramirez.backendcramirez.cliente.application.service;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.dto.ClienteDTO;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository clienteRepository;
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
    public ClienteService(com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository clienteRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, EstadoCivilRepository estadoCivilRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
        this.clienteRepository = clienteRepository;
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


    public boolean existeClientePorNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.existsByNumeroIdentificacion(numeroIdentificacion);
    }

    // Guardar un nuevo cliente
    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {

        Optional<Cliente> clienteExistente = clienteRepository.findByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());

        if (clienteExistente.isPresent()) {

            return convertirA_DTO(clienteExistente.get());
        }

        Cliente cliente = convertirA_Entidad(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertirA_DTO(clienteGuardado);
    }

    // Obtener todos los clientes
    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }

    // Obtener cliente por ID
    public ClienteDTO obtenerClientePorId(int id) {
        return clienteRepository.findById(id)
                .map(this::convertirA_DTO)
                .orElse(null);
    }



    public List<ClienteDTO> obtenerClientesPorOperario(int idOperario) {
        System.out.println("ID Operario recibido en el backend: " + idOperario);

        List<Cliente> clientes = clienteRepository.findByIdOperario(idOperario);
        System.out.println("Número de clientes encontrados: " + clientes.size());

        return clientes.stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }


    // Actualizar cliente
    public ClienteDTO actualizarCliente(int id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setIdEstadoCivil(clienteDTO.getIdEstadoCivil());
                    cliente.setIdNacionalidad(clienteDTO.getIdNacionalidad());
                    cliente.setIdIdentificacion(clienteDTO.getIdIdentificacion());
                    cliente.setOcupacion(clienteDTO.getOcupacion());
                    cliente.setIdResidencia(clienteDTO.getIdResidencia());
                    cliente.setNombresApellidos(clienteDTO.getNombresApellidos());
                    cliente.setDireccion(clienteDTO.getDireccion());
                    cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
                    cliente.setIdPrefijo(clienteDTO.getIdPrefijo());
                    cliente.setCelularCliente(clienteDTO.getCelularCliente());
                    cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
                    cliente.setIdDepartamento(clienteDTO.getIdDepartamento());
                    cliente.setIdProvincia(clienteDTO.getIdProvincia());
                    cliente.setIdDistrito(clienteDTO.getIdDistrito());
                    cliente.setIdOperario(clienteDTO.getIdOperario());
                    return convertirA_DTO(clienteRepository.save(cliente));
                })
                .orElse(null);
    }

    // Eliminar cliente
    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    // Conversión de Cliente a ClienteDTO
    private ClienteDTO convertirA_DTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setIdEstadoCivil(cliente.getIdEstadoCivil());
        dto.setIdIdentificacion(cliente.getIdIdentificacion());
        dto.setIdNacionalidad(cliente.getIdNacionalidad());
        dto.setIdResidencia(cliente.getIdResidencia());
        dto.setIdOperario(cliente.getIdOperario());
        dto.setIdPrefijo(cliente.getIdPrefijo());
        dto.setNombresApellidos(cliente.getNombresApellidos());
        dto.setOcupacion(cliente.getOcupacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setCorreoElectronico(cliente.getCorreoElectronico());
        dto.setCelularCliente(cliente.getCelularCliente());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        dto.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
        dto.setIdDepartamento(cliente.getIdDepartamento());
        dto.setIdProvincia(cliente.getIdProvincia());
        dto.setIdDistrito(cliente.getIdDistrito());

        dto.setOperario(obtenerTexto(operarioRepository.findById(cliente.getIdOperario()), "TipoOperario"));
        dto.setPrefijoPais(obtenerTexto(prefijotelefonicoRepository.findById(cliente.getIdPrefijo()), "PrefijoPais"));
        dto.setDocumentoIdentificacion(obtenerTexto(identificacionRepository.findById(cliente.getIdIdentificacion()), "DocumentoIdentificacion"));
        dto.setEstadoCivil(obtenerTexto(estadoCivilRepository.findById(cliente.getIdEstadoCivil()), "EstadoCivil"));
        dto.setNacionalidad(obtenerTexto(nacionalidadRepository.findById(cliente.getIdNacionalidad()), "NombreNacionalidad"));
        dto.setResidencia(obtenerTexto(residenciaRepository.findById(cliente.getIdResidencia()), "Residencia"));
        dto.setDepartamento(obtenerTexto(departamentoRepository.findById(cliente.getIdDepartamento()), "NombreDepartamento"));
        dto.setProvincia(obtenerTexto(provinciaRepository.findById(cliente.getIdProvincia()), "NombreProvincia"));
        dto.setDistrito(obtenerTexto(distritoRepository.findById(cliente.getIdDistrito()), "NombreDistrito"));

        return dto;
    }

    // Conversión de ClienteDTO a Cliente
    private Cliente convertirA_Entidad(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setIdEstadoCivil(dto.getIdEstadoCivil());
        cliente.setIdNacionalidad(dto.getIdNacionalidad());
        cliente.setIdIdentificacion(dto.getIdIdentificacion());
        cliente.setIdResidencia(dto.getIdResidencia());
        cliente.setFechaRegistro(dto.getFechaRegistro());
        cliente.setOcupacion(dto.getOcupacion());
        cliente.setNombresApellidos(dto.getNombresApellidos());
        cliente.setDireccion(dto.getDireccion());
        cliente.setIdPrefijo(dto.getIdPrefijo());
        cliente.setIdOperario(dto.getIdOperario());
        cliente.setCorreoElectronico(dto.getCorreoElectronico());
        cliente.setCelularCliente(dto.getCelularCliente());
        cliente.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        cliente.setIdDepartamento(dto.getIdDepartamento());
        cliente.setIdProvincia(dto.getIdProvincia());
        cliente.setIdDistrito(dto.getIdDistrito());
        return cliente;
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

    // Obtener clientes por operario en una fecha específica
    public List<ClienteDTO> obtenerClientesPorOperarioYFecha(int idOperario, LocalDate fecha) {
        return clienteRepository.findByIdOperarioAndFechaRegistro(idOperario, fecha)
                .stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }



}
