package com.cramirez.backendcramirez.cliente.application.service;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.dto.ClienteConLotesDTO;
import com.cramirez.backendcramirez.cliente.dto.ClienteDTO;
import com.cramirez.backendcramirez.cuotaExtraordinaria.domain.entity.CuotaExtraordinaria;
import com.cramirez.backendcramirez.cuotaExtraordinaria.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.cuotaExtraordinaria.infrastructure.repository.CuotaExtraordinariaRepository;
import com.cramirez.backendcramirez.documento.infrastructure.repository.IdentificacionRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.*;
import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import com.cramirez.backendcramirez.lote.domain.entity.Lote;
import com.cramirez.backendcramirez.lote.dto.LinderoDTO;
import com.cramirez.backendcramirez.lote.dto.LoteDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.LinderoRepository;
import com.cramirez.backendcramirez.matriz.domain.entity.Matriz;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import com.cramirez.backendcramirez.matriz.infrastructure.repository.MatrizRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.EstadoCivilRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.NacionalidadRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.PrefijotelefonicoRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.TipoContratoRepository;
import com.cramirez.backendcramirez.operario.infrastructure.repository.OperarioRepository;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.TipoProyectoRepository;
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
    private final TipoProyectoRepository tipoProyectoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final TipoContratoRepository tipoContratoRepository;
    private final MatrizRepository matrizRepository;
    private final CuotaExtraordinariaRepository cuotaExtraordinariaRepository;
    private final LinderoRepository linderoRepository;



    @Autowired
    public ClienteService(com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository clienteRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, EstadoCivilRepository estadoCivilRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, TipoProyectoRepository tipoProyectoRepository, UbicacionRepository ubicacionRepository, TipoContratoRepository tipoContratoRepository, MatrizRepository matrizRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository) {
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
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.tipoContratoRepository = tipoContratoRepository;
        this.matrizRepository = matrizRepository;
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.linderoRepository = linderoRepository;
    }


    public boolean existeClientePorNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.existsByNumeroIdentificacion(numeroIdentificacion);
    }

    public Optional<ClienteDTO> obtenerClienteDTOporNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion)
                .map(this::convertirA_DTO);
    }




    public List<ClienteConLotesDTO> obtenerClientesConLotes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(cliente -> {
            ClienteDTO clienteDTO = convertirA_DTO(cliente);

            List<LoteDTO> lotes = cliente.getLotes().stream()
                    .map(this::mapearLoteALoteDTO)
                    .collect(Collectors.toList());

            ClienteConLotesDTO dto = new ClienteConLotesDTO();
            dto.setCliente(clienteDTO);
            dto.setLotes(lotes);

            return dto;
        }).collect(Collectors.toList());
    }

    private LoteDTO mapearLoteALoteDTO(Lote lote) {
        LoteDTO dto = new LoteDTO();
        dto.setIdLote(lote.getIdLote());
        dto.setIdUbicacion(lote.getIdUbicacion());
        dto.setManzana(lote.getManzana());
        dto.setNumeroLote(lote.getNumeroLote());
        dto.setIdTipoContrato(lote.getIdTipoContrato());
        dto.setAreaLote(lote.getAreaLote());
        dto.setCostoLote(lote.getCostoLote());
        dto.setMontoLetras(lote.getMontoLetras());
        dto.setPagoInicial(lote.getPagoInicial());
        dto.setSeparacion(lote.getSeparacion());
        dto.setMontoCuotas(lote.getMontoCuotas());
        dto.setCantidadCuotas(lote.getCantidadCuotas());
        dto.setEmpresa(lote.getEmpresa());
        dto.setEmpresaVende(lote.getEmpresaVende());
        dto.setRucVendedor(lote.getRucVendedor());
        dto.setDireccionVendedor(lote.getDireccionVendedor());
        dto.setRepresentanteLegalVendedor(lote.getRepresentanteLegalVendedor());
        dto.setDniVendedor(lote.getDniVendedor());
        dto.setNumeroPartidaPoderVendedor(lote.getNumeroPartidaPoderVendedor());
        dto.setMoneda(lote.getMoneda());
        dto.setNumCuenta(lote.getNumCuenta());
        dto.setCci(lote.getCci());
        dto.setFechaSale(lote.getFechaSale());
        dto.setFechaFirmaContratoDefinitivo(lote.getFechaFirmaContratoDefinitivo());
        dto.setAreaMatrizHas(lote.getAreaMatrizHas());
        dto.setRegistroDe(lote.getRegistroDe());
        dto.setPartidaMatriz(lote.getPartidaMatriz());
        dto.setUbicacionLote(lote.getUbicacionLote());
        dto.setUnidadCatastralMatriz(lote.getUnidadCatastralMatriz());
        dto.setUrbanizacionMatriz(lote.getUrbanizacionMatriz());
        dto.setDistritoMatriz(lote.getDistritoMatriz());
        dto.setProvinciaMatriz(lote.getProvinciaMatriz());
        dto.setDepartamentoMatriz(lote.getDepartamentoMatriz());
        dto.setCompraVentaMatriz(lote.getCompraVentaMatriz());
        dto.setSituacionLegalMatriz(lote.getSituacionLegalMatriz());


        dto.setTipoProyecto(obtenerTexto(tipoProyectoRepository.findById(lote.getIdTipoProyecto()), "TipoProyecto"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(lote.getIdUbicacion()), "Ubicacion"));
        dto.setContrato(obtenerTexto(tipoContratoRepository.findById(lote.getIdTipoContrato()), "TipoContrato"));


        linderoRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirALinderoDTO)
                .ifPresent(dto::setLindero);

        List<CuotaExtraordinariaDTO> cuotas = cuotaExtraordinariaRepository.findByIdLote(lote.getIdLote()).stream()
                .map(this::convertirACuotaDTO)
                .collect(Collectors.toList());
        dto.setCuotasExtraordinarias(cuotas);

        List<MatrizDTO> matriz = matrizRepository.findByIdLote(lote.getIdLote()).stream()
                .map(this::convertirAMatrizDTO)
                .collect(Collectors.toList());
        dto.setMatriz(matriz);

        return dto;
    }


    private LinderoDTO convertirALinderoDTO(Lindero lindero) {
        LinderoDTO dto = new LinderoDTO();
        dto.setIdLindero(lindero.getIdLindero());
        dto.setIdLote(lindero.getIdLote());
        dto.setPorLaDerecha(lindero.getPorLaDerecha());
        dto.setPorLaIzquierda(lindero.getPorLaIzquierda());
        dto.setPorElFrente(lindero.getPorElFrente());
        dto.setPorElFondo(lindero.getPorElFondo());
        return dto;
    }

    private CuotaExtraordinariaDTO convertirACuotaDTO(CuotaExtraordinaria cuota) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(cuota.getIdCuotaExtraordinaria());
        dto.setIdLote(cuota.getIdLote());
        dto.setCuotaExtraordinaria(cuota.getCuotaExtraordinaria());
        dto.setMantenimientoMensual(cuota.getMantenimientoMensual());
        dto.setMantenimientoMensualLetras(cuota.getMantenimientoMensualLetras());
        dto.setEstadoCuenta(cuota.getEstadoCuenta());
        dto.setMontoDeudaLetra(cuota.getMontoDeudaLetra());
        dto.setCuotaPendientePago(cuota.getCuotaPendientePago());
        return dto;
    }

    private MatrizDTO convertirAMatrizDTO(Matriz matriz) {
        MatrizDTO dto = new MatrizDTO();
        dto.setIdMatriz(matriz.getIdMatriz());
        dto.setIdLote(matriz.getIdLote());
        dto.setIdDistrito(matriz.getIdDistrito());
        dto.setIdProvincia(matriz.getIdProvincia());
        dto.setIdDepartamento(matriz.getIdDepartamento());
        dto.setIdUbicacion(matriz.getIdUbicacion());
        dto.setAreaMatrizHas(matriz.getAreaMatrizHas());
        dto.setRegistrosDE(matriz.getRegistrosDE());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastral(matriz.getUnidadCatastral());
        dto.setUrbanizacionMatriz(matriz.getUrbanizacionMatriz());
        dto.setCompraventaMatriz(matriz.getCompraventaMatriz());
        dto.setSituacionLegal(matriz.getSituacionLegal());
        dto.setAlicuota(matriz.getAlicuota());
        dto.setAlicuotaLetras(matriz.getAlicuotaLetras());

        dto.setDepartamento(obtenerTexto(departamentoRepository.findById(matriz.getIdDepartamento()), "NombreDepartamento"));
        dto.setProvincia(obtenerTexto(provinciaRepository.findById(matriz.getIdProvincia()), "NombreProvincia"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(matriz.getIdUbicacion()), "Ubicacion"));
        dto.setDistrito(obtenerTexto(distritoRepository.findById(matriz.getIdDistrito()), "NombreDistrito"));
        return dto;
    }

    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {

        Optional<Cliente> clienteExistente = clienteRepository.findByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());

        if (clienteExistente.isPresent()) {

            return convertirA_DTO(clienteExistente.get());
        }

        Cliente cliente = convertirA_Entidad(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertirA_DTO(clienteGuardado);
    }


    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }


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

    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

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

    public List<ClienteDTO> obtenerClientesPorOperarioYFecha(int idOperario, LocalDate fecha) {
        return clienteRepository.findByIdOperarioAndFechaRegistro(idOperario, fecha)
                .stream()
                .map(this::convertirA_DTO)
                .collect(Collectors.toList());
    }
}
