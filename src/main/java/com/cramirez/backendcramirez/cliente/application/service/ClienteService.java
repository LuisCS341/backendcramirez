package com.cramirez.backendcramirez.cliente.application.service;
import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
import com.cramirez.backendcramirez.auth.infrastructure.repository.CredencialesRepository;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import com.cramirez.backendcramirez.cliente.dto.ClienteConLotesDTO;
import com.cramirez.backendcramirez.cliente.dto.ClienteConyugeDTO;
import com.cramirez.backendcramirez.cliente.dto.ClienteDTO;
import com.cramirez.backendcramirez.cliente.dto.TransferenciaClienteDTO;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteConyugeRepository;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import com.cramirez.backendcramirez.copropietario.infrastructure.repository.CopropietarioRepository;
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
import com.cramirez.backendcramirez.lote.infrastructure.repository.LoteRepository;
import com.cramirez.backendcramirez.matriz.domain.entity.Matriz;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import com.cramirez.backendcramirez.matriz.infrastructure.repository.MatrizRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.EstadoCivilRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.NacionalidadRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.PrefijotelefonicoRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.TipoContratoRepository;
import com.cramirez.backendcramirez.operario.domain.entity.Operario;
import com.cramirez.backendcramirez.operario.infrastructure.repository.OperarioRepository;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.TipoProyectoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final LoteRepository loteRepository;
    private final CredencialesRepository credencialesRepository;
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
    private final TipoProyectoRepository tipoProyectoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final TipoContratoRepository tipoContratoRepository;
    private final MatrizRepository matrizRepository;
    private final CuotaExtraordinariaRepository cuotaExtraordinariaRepository;
    private final LinderoRepository linderoRepository;
    private final ClienteConyugeRepository clienteConyugeRepository;


    @Autowired
    public ClienteService(ClienteRepository clienteRepository, LoteRepository loteRepository, CredencialesRepository credencialesRepository, CopropietarioRepository copropietarioRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, EstadoCivilRepository estadoCivilRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, TipoProyectoRepository tipoProyectoRepository, UbicacionRepository ubicacionRepository, TipoContratoRepository tipoContratoRepository, MatrizRepository matrizRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository, ClienteConyugeRepository clienteConyugeRepository) {
        this.clienteRepository = clienteRepository;
        this.loteRepository = loteRepository;
        this.credencialesRepository = credencialesRepository;
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
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.tipoContratoRepository = tipoContratoRepository;
        this.matrizRepository = matrizRepository;
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.linderoRepository = linderoRepository;
        this.clienteConyugeRepository = clienteConyugeRepository;
    }


    public ClienteConLotesDTO editarClienteYComponentes(ClienteConLotesDTO clienteConLotesDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteConLotesDTO.getCliente().getIdCliente());
        if (!clienteOpt.isPresent()) {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + clienteConLotesDTO.getCliente().getIdCliente());
        }

        Cliente cliente = clienteOpt.get();
        actualizarCliente(cliente, clienteConLotesDTO.getCliente());

        if (clienteConLotesDTO.getCliente().getConyuge() != null) {
            editarConyuge(clienteConLotesDTO.getCliente().getConyuge(), cliente);
        }

        if (clienteConLotesDTO.getCliente().getCopropietarios() != null) {
            for (CopropietarioDTO copropietarioDTO : clienteConLotesDTO.getCliente().getCopropietarios()) {
                editarCopropietario(copropietarioDTO);
            }
        }

        if (clienteConLotesDTO.getLotes() != null) {
            for (LoteDTO loteDTO : clienteConLotesDTO.getLotes()) {
                editarLote(loteDTO);
            }
        }

        List<LoteDTO> lotes = cliente.getLotes().stream()
                .map(this::mapearLoteALoteDTO)
                .collect(Collectors.toList());

        ClienteConLotesDTO clienteConLotesDTOResult = new ClienteConLotesDTO();
        clienteConLotesDTOResult.setCliente(convertirAClienteDTO(cliente));
        clienteConLotesDTOResult.setLotes(lotes);

        return clienteConLotesDTOResult;
    }


    private void actualizarCliente(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setIdEstadoCivil(clienteDTO.getIdEstadoCivil());
        cliente.setIdNacionalidad(clienteDTO.getIdNacionalidad());
        cliente.setIdIdentificacion(clienteDTO.getIdIdentificacion());
        cliente.setIdResidencia(clienteDTO.getIdResidencia());
        cliente.setOcupacion(clienteDTO.getOcupacion());
        cliente.setNombresApellidos(clienteDTO.getNombresApellidos());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setIdPrefijo(clienteDTO.getIdPrefijo());
        cliente.setIdOperario(clienteDTO.getIdOperario());
        cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
        cliente.setCelularCliente(clienteDTO.getCelularCliente());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setIdDepartamento(clienteDTO.getIdDepartamento());
        cliente.setIdProvincia(clienteDTO.getIdProvincia());
        cliente.setIdDistrito(clienteDTO.getIdDistrito());

        clienteRepository.save(cliente);
    }

    // Editar cónyuge
    private void editarConyuge(ClienteConyugeDTO conyugeDTO, Cliente cliente) {
        Optional<ClienteConyuge> conyugeOpt = clienteConyugeRepository.findById(cliente.getIdCliente());
        if (conyugeOpt.isPresent()) {
            ClienteConyuge conyuge = conyugeOpt.get();
            conyuge.setNombresApellidosConyuge(conyugeDTO.getNombresApellidosConyuge());
            conyuge.setOcupacionConyuge(conyugeDTO.getOcupacionConyuge());
            conyuge.setCorreoElectronicoConyuge(conyugeDTO.getCorreoElectronicoConyuge());
            clienteConyugeRepository.save(conyuge);
        }
    }

    // Editar copropietarios
    private void editarCopropietario(CopropietarioDTO copropietarioDTO) {
        Optional<Copropietario> copropietarioOpt = copropietarioRepository.findById(copropietarioDTO.getIdCopropietario());
        if (copropietarioOpt.isPresent()) {
            Copropietario copropietario = copropietarioOpt.get();
            copropietario.setNombresApellidosCopropietarios(copropietarioDTO.getNombresApellidosCopropietarios());
            copropietarioRepository.save(copropietario);
        }
    }


    // Editar lotes
    private void editarLote(LoteDTO loteDTO) {
        Optional<Lote> loteOpt = loteRepository.findById(loteDTO.getIdLote());
        if (loteOpt.isPresent()) {
            Lote lote = loteOpt.get();
            lote.setManzana(loteDTO.getManzana());
            lote.setNumeroLote(loteDTO.getNumeroLote());

            loteRepository.save(lote);

            if (loteDTO.getLindero() != null) {
                editarLindero(loteDTO.getLindero());
            }

            if (loteDTO.getCuotasExtraordinarias() != null) {
                for (CuotaExtraordinariaDTO cuotaDTO : loteDTO.getCuotasExtraordinarias()) {
                    editarCuotaExtraordinaria(cuotaDTO);
                }
            }

            if (loteDTO.getMatriz() != null) {
                for (MatrizDTO matrizDTO : loteDTO.getMatriz()) {
                    editarMatriz(matrizDTO);
                }
            }
        }
    }

    // Editar lindero
    private void editarLindero(LinderoDTO linderoDTO) {
        Optional<Lindero> linderoOpt = linderoRepository.findById(linderoDTO.getIdLindero());
        if (linderoOpt.isPresent()) {
            Lindero lindero = linderoOpt.get();
            lindero.setIdLote(linderoDTO.getIdLote());
            lindero.setPorLaDerecha(linderoDTO.getPorLaDerecha());
            lindero.setPorLaIzquierda(linderoDTO.getPorLaIzquierda());
            lindero.setPorElFrente(linderoDTO.getPorElFrente());
            lindero.setPorElFondo(linderoDTO.getPorElFondo());
            linderoRepository.save(lindero);
        }
    }

    // Editar cuota extraordinaria
    private void editarCuotaExtraordinaria(CuotaExtraordinariaDTO cuotaDTO) {
        Optional<CuotaExtraordinaria> cuotaOpt = cuotaExtraordinariaRepository.findById(cuotaDTO.getIdCuotaExtraordinaria());
        if (cuotaOpt.isPresent()) {
            CuotaExtraordinaria cuota = cuotaOpt.get();
            cuota.setIdCuotaExtraordinaria(cuotaDTO.getIdCuotaExtraordinaria());
            cuota.setIdLote(cuotaDTO.getIdLote());
            cuota.setCantidadCuotaExtraordinaria(cuotaDTO.getCantidadCuotaExtraordinaria());
            cuota.setMontoCuotaExtraordinaria(cuotaDTO.getMontoCuotaExtraordinaria());
            cuota.setMantenimientoMensual(cuotaDTO.getMantenimientoMensual());
            cuota.setMantenimientoMensualLetras(cuotaDTO.getMantenimientoMensualLetras());
            cuota.setEstadoCuenta(cuotaDTO.getEstadoCuenta());
            cuota.setMontoDeudaLetra(cuotaDTO.getMontoDeudaLetra());
            cuota.setCuotaPendientePago(cuotaDTO.getCuotaPendientePago());
            cuota.setLetrasPendientePago(cuotaDTO.getLetrasPendientePago());
            cuota.setFechaEntrega(cuotaDTO.getFechaEntrega());
            cuota.setCartaNoAdeudo(cuotaDTO.getCartaNoAdeudo());
            cuota.setCertificadoLote(cuotaDTO.getCertificadoLote());
            cuota.setMediosPago(cuotaDTO.getMediosPago());
            cuota.setPlano1(cuotaDTO.getPlano1());
            cuota.setPlano2(cuotaDTO.getPlano2());
            cuota.setEnvioMinuta(cuotaDTO.getEnvioMinuta());
            cuota.setFechaCita(cuotaDTO.getFechaCita());
            cuota.setHoraCita(cuotaDTO.getHoraCita());
            cuota.setModificarMinuta(cuotaDTO.getModificarMinuta());
            cuota.setMinutaEscaneada(cuotaDTO.getMinutaEscaneada());
            cuota.setExpNotaria(cuotaDTO.getExpNotaria());

            cuotaExtraordinariaRepository.save(cuota);
        }
    }

    // Editar matriz
    private void editarMatriz(MatrizDTO matrizDTO) {
        Optional<Matriz> matrizOpt = matrizRepository.findById(matrizDTO.getIdMatriz());
        if (matrizOpt.isPresent()) {
            Matriz matriz = matrizOpt.get();
            matriz.setIdLote(matrizDTO.getIdLote());
            matriz.setIdDistrito(matrizDTO.getIdDistrito());
            matriz.setIdProvincia(matrizDTO.getIdProvincia());
            matriz.setIdDepartamento(matrizDTO.getIdDepartamento());
            matriz.setIdUbicacion(matrizDTO.getIdUbicacion());
            matriz.setAreaMatrizHas(matrizDTO.getAreaMatrizHas());
            matriz.setRegistrosDE(matrizDTO.getRegistrosDE());
            matriz.setPartidaMatriz(matrizDTO.getPartidaMatriz());
            matriz.setUnidadCatastral(matrizDTO.getUnidadCatastral());
            matriz.setUrbanizacionMatriz(matrizDTO.getUrbanizacionMatriz());
            matriz.setCompraventaMatriz(matrizDTO.getCompraventaMatriz());
            matriz.setSituacionLegal(matrizDTO.getSituacionLegal());
            matriz.setAlicuota(matrizDTO.getAlicuota());
            matriz.setAlicuotaLetras(matrizDTO.getAlicuotaLetras());
            matrizRepository.save(matriz);
        }
    }

    public boolean existeClientePorNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.existsByNumeroIdentificacion(numeroIdentificacion);
    }

    public Optional<ClienteDTO> obtenerClienteDTOporNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.findByNumeroIdentificacion(numeroIdentificacion)
                .map(this::convertirAClienteDTO);
    }




    public List<ClienteConLotesDTO> obtenerClientesConLotes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(cliente -> {
            ClienteDTO clienteDTO = convertirAClienteDTO(cliente);

            List<LoteDTO> lotes = cliente.getLotes().stream()
                    .map(this::mapearLoteALoteDTO)
                    .collect(Collectors.toList());

            ClienteConLotesDTO dto = new ClienteConLotesDTO();
            dto.setCliente(clienteDTO);
            dto.setLotes(lotes);

            return dto;
        }).collect(Collectors.toList());
    }


    public List<ClienteConLotesDTO> obtenerClientesConLotesPorOperario(int idOperario) {
        List<Cliente> clientes = clienteRepository.findByIdOperario(idOperario);

        return clientes.stream().map(cliente -> {
            ClienteDTO clienteDTO = convertirAClienteDTO(cliente);

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
        dto.setCodigoLoteCliente(lote.getCodigoLoteCliente());
        dto.setManzana(lote.getManzana());
        dto.setNumeroLote(lote.getNumeroLote());
        dto.setIdTipoContrato(lote.getIdTipoContrato());
        dto.setAreaLote(lote.getAreaLote());
        dto.setAreaLoteLetras(lote.getAreaLoteLetras());
        dto.setCostoLote(lote.getCostoLote());
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
        dto.setUbicacionLote(lote.getUbicacionLote());
        dto.setFechaInicioContrato(lote.getFechaInicioContrato());
        dto.setFechaCancelacionContrato(lote.getFechaCancelacionContrato());
        dto.setCantidadCuotaCuentaRecaudadora(lote.getCantidadCuotaCuentaRecaudadora());


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
        dto.setCantidadCuotaExtraordinaria(cuota.getCantidadCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinaria(cuota.getMontoCuotaExtraordinaria());
        dto.setMantenimientoMensual(cuota.getMantenimientoMensual());
        dto.setMantenimientoMensualLetras(cuota.getMantenimientoMensualLetras());
        dto.setEstadoCuenta(cuota.getEstadoCuenta());
        dto.setMontoDeudaLetra(cuota.getMontoDeudaLetra());
        dto.setCuotaPendientePago(cuota.getCuotaPendientePago());
        dto.setLetrasPendientePago(cuota.getLetrasPendientePago());
        dto.setFechaEntrega(cuota.getFechaEntrega());
        dto.setCartaNoAdeudo(cuota.getCartaNoAdeudo());
        dto.setCertificadoLote(cuota.getCertificadoLote());
        dto.setMediosPago(cuota.getMediosPago());
        dto.setPlano1(cuota.getPlano1());
        dto.setPlano2(cuota.getPlano2());
        dto.setEnvioMinuta(cuota.getEnvioMinuta());
        dto.setFechaCita(cuota.getFechaCita());
        dto.setHoraCita(cuota.getHoraCita());
        dto.setModificarMinuta(cuota.getModificarMinuta());
        dto.setMinutaEscaneada(cuota.getMinutaEscaneada());
        dto.setExpNotaria(cuota.getExpNotaria());
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

            return convertirAClienteDTO(clienteExistente.get());
        }

        Cliente cliente = convertirAClienteEntidad(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertirAClienteDTO(clienteGuardado);
    }


    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }


    public ClienteDTO obtenerClientePorId(int id) {
        return clienteRepository.findById(id)
                .map(this::convertirAClienteDTO)
                .orElse(null);
    }







    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
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

        List<CopropietarioDTO> copropietarios = copropietarioRepository.findByIdClienteCopropietarios(cliente.getIdCliente())
                .stream()
                .map(this::convertirACopropietarioDTO)
                .collect(Collectors.toList());
        dto.setCopropietarios(copropietarios);

        Optional<ClienteConyuge> conyugeOpt = clienteConyugeRepository.findByIdCliente(cliente.getIdCliente());
        if (conyugeOpt.isPresent()) {
            ClienteConyugeDTO conyugeDTO = convertirAClienteConyugeDTO(conyugeOpt.get());
            dto.setConyuge(conyugeDTO);
        } else {
            dto.setConyuge(null);
        }

        return dto;
    }

    private ClienteConyugeDTO convertirAClienteConyugeDTO(ClienteConyuge clienteConyuge) {
        ClienteConyugeDTO dto = new ClienteConyugeDTO();
        dto.setIdCliente(clienteConyuge.getIdCliente());
        dto.setIdClienteConyuge(clienteConyuge.getIdClienteConyuge());
        dto.setIdNacionalidadConyuge(clienteConyuge.getIdNacionalidadConyuge());
        dto.setIdPrefijoConyuge(clienteConyuge.getIdPrefijoConyuge());
        dto.setOcupacionConyuge(clienteConyuge.getOcupacionConyuge());
        dto.setIdIdentificacionConyuge(clienteConyuge.getIdIdentificacionConyuge());
        dto.setIdResidenciaConyuge(clienteConyuge.getIdResidenciaConyuge());
        dto.setIdOperarioConyuge(clienteConyuge.getIdOperarioConyuge());
        dto.setIdDepartamentoConyuge(clienteConyuge.getIdDepartamentoConyuge());
        dto.setIdProvinciaConyuge(clienteConyuge.getIdProvinciaConyuge());
        dto.setIdDistritoConyuge(clienteConyuge.getIdDistritoConyuge());
        dto.setNombresApellidosConyuge(clienteConyuge.getNombresApellidosConyuge());
        dto.setDireccionConyuge(clienteConyuge.getDireccionConyuge());
        dto.setCorreoElectronicoConyuge(clienteConyuge.getCorreoElectronicoConyuge());
        dto.setCelularConyuge(clienteConyuge.getCelularConyuge());
        dto.setNumeroIdentificacionConyuge(clienteConyuge.getNumeroIdentificacionConyuge());


        dto.setOperarioConyuge(obtenerTexto(operarioRepository.findById(clienteConyuge.getIdOperarioConyuge()), "TipoOperario"));
        dto.setPrefijoPaisConyuge(obtenerTexto(prefijotelefonicoRepository.findById(clienteConyuge.getIdPrefijoConyuge()), "PrefijoPais"));
        dto.setDocumentoIdentificacionConyuge(obtenerTexto(identificacionRepository.findById(clienteConyuge.getIdIdentificacionConyuge()), "DocumentoIdentificacion"));
        dto.setNacionalidadConyuge(obtenerTexto(nacionalidadRepository.findById(clienteConyuge.getIdNacionalidadConyuge()), "NombreNacionalidad"));
        dto.setResidenciaConyuge(obtenerTexto(residenciaRepository.findById(clienteConyuge.getIdResidenciaConyuge()), "Residencia"));
        dto.setDepartamentoConyuge(obtenerTexto(departamentoRepository.findById(clienteConyuge.getIdDepartamentoConyuge()), "NombreDepartamento"));
        dto.setProvinciaConyuge(obtenerTexto(provinciaRepository.findById(clienteConyuge.getIdProvinciaConyuge()), "NombreProvincia"));
        dto.setDistritoConyuge(obtenerTexto(distritoRepository.findById(clienteConyuge.getIdDistritoConyuge()), "NombreDistrito"));


        return dto;
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



    private Cliente convertirAClienteEntidad(ClienteDTO dto) {
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
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }


    public void transferirCliente(int idCliente, TransferenciaClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Credenciales credencial = credencialesRepository.findByUsuario(dto.getNuevoUsuarioOperario())
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con usuario: " + dto.getNuevoUsuarioOperario()));

        Operario nuevoOperario = credencial.getOperario();

        if (Objects.equals(
                cliente.getOperario() != null ? cliente.getOperario().getIdOperario() : null,
                nuevoOperario != null ? nuevoOperario.getIdOperario() : null)) {
            throw new RuntimeException("El cliente ya pertenece a ese operario");
        }

        cliente.setIdOperario(nuevoOperario.getIdOperario());
        clienteRepository.save(cliente);
    }


}
