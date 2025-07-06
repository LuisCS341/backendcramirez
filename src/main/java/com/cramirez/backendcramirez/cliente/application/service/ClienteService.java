package com.cramirez.backendcramirez.cliente.application.service;
import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
import com.cramirez.backendcramirez.auth.infrastructure.repository.CredencialesRepository;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import com.cramirez.backendcramirez.cliente.dto.*;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteConyugeRepository;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import com.cramirez.backendcramirez.copropietario.infrastructure.repository.CopropietarioRepository;
import com.cramirez.backendcramirez.lote.domain.entity.*;
import com.cramirez.backendcramirez.lote.dto.*;
import com.cramirez.backendcramirez.lote.infrastructure.repository.*;
import com.cramirez.backendcramirez.documento.infrastructure.repository.IdentificacionRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.*;
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

import java.util.*;
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
    private final CuotaExtraordinariaRepository cuotaExtraordinariaRepository;
    private final LinderoRepository linderoRepository;
    private final MatrizRepository matrizRepository;
    private final ClienteConyugeRepository clienteConyugeRepository;
    private final CuotaRepository cuotaRepository;


    @Autowired
    public ClienteService(ClienteRepository clienteRepository, LoteRepository loteRepository, CredencialesRepository credencialesRepository, CopropietarioRepository copropietarioRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, EstadoCivilRepository estadoCivilRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, TipoProyectoRepository tipoProyectoRepository, UbicacionRepository ubicacionRepository, TipoContratoRepository tipoContratoRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository, MatrizRepository matrizRepository, ClienteConyugeRepository clienteConyugeRepository, CuotaRepository cuotaRepository) {
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
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.linderoRepository = linderoRepository;
        this.matrizRepository = matrizRepository;
        this.clienteConyugeRepository = clienteConyugeRepository;
        this.cuotaRepository = cuotaRepository;
    }

    public LoteConClienteCompletoDTO editarClienteYComponentes(LoteConClienteCompletoDTO loteConClienteCompletoDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(loteConClienteCompletoDTO.getCliente().getIdCliente());
        if (!clienteOpt.isPresent()) {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + loteConClienteCompletoDTO.getCliente().getIdCliente());
        }

        Cliente cliente = clienteOpt.get();

        actualizarCliente(cliente, loteConClienteCompletoDTO.getCliente());

        if (loteConClienteCompletoDTO.getCliente().getConyuge() != null) {
            editarClienteConyuge(loteConClienteCompletoDTO.getCliente().getConyuge(), cliente);
        }

        if (loteConClienteCompletoDTO.getCliente().getCopropietarios() != null) {
            for (CopropietarioDTO copropietarioDTO : loteConClienteCompletoDTO.getCliente().getCopropietarios()) {
                editarCopropietario(copropietarioDTO);
            }
        }

        if (loteConClienteCompletoDTO.getLote() != null) {
            editarLote(loteConClienteCompletoDTO.getLote());
        }

        LoteConClienteCompletoDTO loteConClienteCompletoDTOResult = new LoteConClienteCompletoDTO();
        loteConClienteCompletoDTOResult.setCliente(convertirAClienteDTO(cliente));
        loteConClienteCompletoDTOResult.setLote(loteConClienteCompletoDTO.getLote());

        return loteConClienteCompletoDTOResult;
    }



    private void actualizarCliente(Cliente cliente, ClienteDTO clienteDTO) {
        cliente.setFechaRegistro(clienteDTO.getFechaRegistro());
        cliente.setOcupacion(clienteDTO.getOcupacion());
        cliente.setNombresApellidos(clienteDTO.getNombresApellidos());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setCorreoElectronico(clienteDTO.getCorreoElectronico());
        cliente.setCelularCliente(clienteDTO.getCelularCliente());
        cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
        cliente.setDescripcionEstadoCivil(clienteDTO.getDescripcionEstadoCivil());

        clienteRepository.save(cliente);
    }


    private void editarClienteConyuge(ClienteConyugeDTO conyugeDTO, Cliente cliente) {
        Optional<ClienteConyuge> conyugeOpt = clienteConyugeRepository.findById(conyugeDTO.getIdClienteConyuge());
        if (conyugeOpt.isPresent()) {
            ClienteConyuge conyuge = conyugeOpt.get();
            conyuge.setOcupacionConyuge(conyugeDTO.getOcupacionConyuge());
            conyuge.setNombresApellidosConyuge(conyugeDTO.getNombresApellidosConyuge());
            conyuge.setDireccionConyuge(conyugeDTO.getDireccionConyuge());
            conyuge.setNumeroIdentificacionConyuge(conyugeDTO.getNumeroIdentificacionConyuge());
            clienteConyugeRepository.save(conyuge);
        }
    }

    private void editarCopropietario(CopropietarioDTO copropietarioDTO) {
        Optional<Copropietario> copropietarioOpt = copropietarioRepository.findById(copropietarioDTO.getIdCopropietario());
        if (copropietarioOpt.isPresent()) {
            Copropietario copropietario = copropietarioOpt.get();
            copropietario.setOcupacionCopropietarios(copropietarioDTO.getOcupacionCopropietarios());
            copropietario.setNombresApellidosCopropietarios(copropietarioDTO.getNombresApellidosCopropietarios());
            copropietario.setDireccionCopropietarios(copropietarioDTO.getDireccionCopropietarios());
            copropietario.setNumeroIdentificacionCopropietarios(copropietarioDTO.getNumeroIdentificacionCopropietarios());
            copropietario.setDescripcionEstadoCivilCopropietarios(copropietarioDTO.getDescripcionEstadoCivilCopropietarios());
            copropietarioRepository.save(copropietario);
        }
    }



    private void editarLote(LoteDTO loteDTO) {
        Optional<Lote> loteOpt = loteRepository.findById(loteDTO.getIdLote());
        if (loteOpt.isPresent()) {
            Lote lote = loteOpt.get();
            lote.setManzana(loteDTO.getManzana());
            lote.setNumeroLote(loteDTO.getNumeroLote());
            lote.setAreaLote(loteDTO.getAreaLote());
            lote.setAreaLoteLetras(loteDTO.getAreaLoteLetras());
            lote.setCostoLote(loteDTO.getCostoLote());
            lote.setCostoLoteLetras(loteDTO.getCostoLoteLetras());
            lote.setEmpresa(loteDTO.getEmpresa());
            lote.setEmpresaVende(loteDTO.getEmpresaVende());
            lote.setRucVendedor(loteDTO.getRucVendedor());
            lote.setDireccionVendedor(loteDTO.getDireccionVendedor());
            lote.setRepresentanteLegalVendedor(loteDTO.getRepresentanteLegalVendedor());
            lote.setDniVendedor(loteDTO.getDniVendedor());
            lote.setNumeroPartidaPoderVendedor(loteDTO.getNumeroPartidaPoderVendedor());
            lote.setMoneda(loteDTO.getMoneda());
            lote.setNumCuenta(loteDTO.getNumCuenta());
            lote.setCci(loteDTO.getCci());
            lote.setFechaSale(loteDTO.getFechaSale());
            lote.setFechaFirmaContratoDefinitivo(loteDTO.getFechaFirmaContratoDefinitivo());
            lote.setUbicacionLote(loteDTO.getUbicacionLote());
            lote.setFechaInicioContrato(loteDTO.getFechaInicioContrato());
            lote.setFechaCancelacionContrato(loteDTO.getFechaCancelacionContrato());
            lote.setPrecioMetroCuadrado(loteDTO.getPrecioMetroCuadrado());
            lote.setPrecioMetroCuadradoLetras(loteDTO.getPrecioMetroCuadradoLetras());
            lote.setTipoRepresentante(loteDTO.getTipoRepresentante());
            lote.setMantenimientoMensual(loteDTO.getMantenimientoMensual());
            lote.setMantenimientoMensualLetras(loteDTO.getMantenimientoMensualLetras());
            lote.setEstadoCuenta(loteDTO.getEstadoCuenta());
            lote.setMontoDeudaLetra(loteDTO.getMontoDeudaLetra());
            lote.setFechaEntrega(loteDTO.getFechaEntrega());

            loteRepository.save(lote);

            if (loteDTO.getLindero() != null) {
                editarLindero(loteDTO.getLindero());
            }

            if (loteDTO.getCuota() != null) {
                editarCuota(loteDTO.getCuota());
            }

            if (loteDTO.getCuotasExtraordinarias() != null) {
                for (CuotaExtraordinariaDTO cuotaDTO : loteDTO.getCuotasExtraordinarias()) {
                    editarCuotaExtraordinaria(cuotaDTO);
                }
            }

        }
    }

    private void editarLindero(LinderoDTO linderoDTO) {
        Optional<Lindero> linderoOpt = linderoRepository.findById(linderoDTO.getIdLindero());
        if (linderoOpt.isPresent()) {
            Lindero lindero = linderoOpt.get();
            lindero.setPorLaDerecha(linderoDTO.getPorLaDerecha());
            lindero.setPorLaIzquierda(linderoDTO.getPorLaIzquierda());
            lindero.setPorElFrente(linderoDTO.getPorElFrente());
            lindero.setPorElFondo(linderoDTO.getPorElFondo());
            linderoRepository.save(lindero);
        }
    }

    private void editarMatriz(MatrizDTO matrizDTO) {
        Optional<Matriz> matrizOpt = matrizRepository.findById(matrizDTO.getIdMatriz());
        if (matrizOpt.isPresent()) {
            Matriz matriz = matrizOpt.get();
            matriz.setIdMatriz(matrizDTO.getIdMatriz());
            matriz.setIdLote(matrizDTO.getIdLote());
            matriz.setIdDepartamento(matrizDTO.getIdDepartamento());
            matriz.setIdProvincia(matrizDTO.getIdProvincia());
            matriz.setIdDistrito(matrizDTO.getIdDistrito());
            matriz.setUbicacion(matrizDTO.getUbicacion());
            matriz.setAreaMatrizHas(matrizDTO.getAreaMatrizHas());
            matriz.setRegistro(matrizDTO.getRegistro());
            matriz.setPartidaMatriz(matrizDTO.getPartidaMatriz());
            matriz.setUnidadCatastral(matrizDTO.getUnidadCatastral());
            matriz.setUrbanizacion(matrizDTO.getUrbanizacion());
            matriz.setCompraventa(matrizDTO.getCompraventa());
            matriz.setSituacionLegal(matrizDTO.getSituacionLegal());
            matrizRepository.save(matriz);
        }
    }

    private void editarCuota(CuotaDTO cuotaDTO) {
        Optional<Cuota> cuotaOpt = cuotaRepository.findById(cuotaDTO.getIdCuota());
        if (cuotaOpt.isPresent()) {
            Cuota cuota = cuotaOpt.get();
            cuota.setIdLote(cuotaDTO.getIdLote());
            cuota.setIdCuota(cuotaDTO.getIdCuota());
            cuota.setLetrasPendientePago(cuotaDTO.getLetrasPendientePago());
            cuota.setCuentaRecaudadora(cuotaDTO.getCuentaRecaudadora());
            cuota.setCuotaInicialIncluyeSeparacion(cuotaDTO.getCuotaInicialIncluyeSeparacion());
            cuota.setCuotaInicialIncluyeSeparacionLetras(cuotaDTO.getCuotaInicialIncluyeSeparacionLetras());
            cuota.setMontoCuotas(cuotaDTO.getMontoCuotas());
            cuota.setMontoCuotaLetras(cuotaDTO.getMontoCuotaLetras());
            cuota.setFechaPago(cuotaDTO.getFechaPago());
            cuota.setCuotaInicialBanco(cuotaDTO.getCuotaInicialBanco());
            cuota.setCantidadCuotas(cuotaDTO.getCantidadCuotas());
            cuota.setCantidadCuotaLetras(cuotaDTO.getCantidadCuotaLetras());
            cuota.setCantidadCuotaCuentaRecaudadora(cuotaDTO.getCantidadCuotaCuentaRecaudadora());
            cuota.setCantidadCuotaBanco(cuotaDTO.getCantidadCuotaBanco());
            cuota.setCuotaPendientePago(cuotaDTO.getCuotaPendientePago());
            cuotaRepository.save(cuota);
        }
    }

    // Editar cuota extraordinaria
    private void editarCuotaExtraordinaria(CuotaExtraordinariaDTO cuotaDTO) {
        Optional<CuotaExtraordinaria> cuotaOpt = cuotaExtraordinariaRepository.findById(cuotaDTO.getIdCuotaExtraordinaria());
        if (cuotaOpt.isPresent()) {
            CuotaExtraordinaria cuota = cuotaOpt.get();
            cuota.setCantidadCuotaExtraordinaria(cuotaDTO.getCantidadCuotaExtraordinaria());
            cuota.setMontoCuotaExtraordinaria(cuotaDTO.getMontoCuotaExtraordinaria());
            cuota.setMediosPago(cuotaDTO.getMediosPago());
            cuotaExtraordinariaRepository.save(cuota);
        }
    }


    public boolean existeClientePorNumeroIdentificacion(String numeroIdentificacion) {
        return clienteRepository.existsByNumeroIdentificacion(numeroIdentificacion);
    }

    public Optional<ClienteDTO> obtenerClienteDTOporNumeroIdentificacion(String numeroIdentificacion) {
        List<Cliente> clientes = clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);

        if (clientes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(convertirAClienteDTO(clientes.get(0)));
    }





    public List<LoteConClienteCompletoDTO> obtenerClientesConLotes() {
        List<Cliente> clientes = clienteRepository.findAll();

        List<LoteConClienteCompletoDTO> resultado = new ArrayList<>();

        for (Cliente cliente : clientes) {
            for (Lote lote : cliente.getLotes()) {
                LoteConClienteCompletoDTO dto = new LoteConClienteCompletoDTO();
                dto.setCliente(convertirAClienteDTO(cliente));
                dto.setLote(mapearLoteALoteDTO(lote));

                resultado.add(dto);
            }
        }

        return resultado;
    }


    public List<LoteConClienteCompletoDTO> obtenerClientesConLotesPorOperario(int idOperario) {
            List<Cliente> clientes = clienteRepository.findByIdOperario(idOperario);
            List<LoteConClienteCompletoDTO> resultado = new ArrayList<>();

            for (Cliente cliente : clientes) {
                for (Lote lote : cliente.getLotes()) {
                    LoteConClienteCompletoDTO dto = new LoteConClienteCompletoDTO();
                    dto.setCliente(convertirAClienteDTO(cliente));
                    dto.setLote(mapearLoteALoteDTO(lote));
                    resultado.add(dto);
                }
            }

            return resultado;
    }



    private LoteDTO mapearLoteALoteDTO(Lote lote) {
    LoteDTO dto = new LoteDTO();
        dto.setIdLote(lote.getIdLote());
        dto.setIdOperario(lote.getIdOperario());
        dto.setIdClienteLote(lote.getIdClienteLote());
        dto.setIdClienteClone(lote.getIdClienteClone());
        dto.setCodigoLoteCliente(lote.getCodigoLoteCliente());
        dto.setIdTipoProyecto(lote.getIdTipoProyecto());
        dto.setIdTipoContrato(lote.getIdTipoContrato());
        dto.setIdUbicacion(lote.getIdUbicacion());
        dto.setManzana(lote.getManzana());
        dto.setNumeroLote(lote.getNumeroLote());
        dto.setAreaLote(lote.getAreaLote());
        dto.setAreaLoteLetras(lote.getAreaLoteLetras());
        dto.setCostoLote(lote.getCostoLote());
        dto.setCostoLoteLetras(lote.getCostoLoteLetras());
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
        dto.setPrecioMetroCuadrado(lote.getPrecioMetroCuadrado());
        dto.setPrecioMetroCuadradoLetras(lote.getPrecioMetroCuadradoLetras());
        dto.setTipoRepresentante(lote.getTipoRepresentante());
        dto.setMantenimientoMensual(lote.getMantenimientoMensual());
        dto.setMantenimientoMensualLetras(lote.getMantenimientoMensualLetras());
        dto.setEstadoCuenta(lote.getEstadoCuenta());
        dto.setMontoDeudaLetra(lote.getMontoDeudaLetra());
        dto.setFechaEntrega(lote.getFechaEntrega());
        dto.setAlicuota(lote.getAlicuota());
        dto.setAlicuotaLetras(lote.getAlicuotaLetras());

        dto.setTipoProyecto(obtenerTexto(tipoProyectoRepository.findById(lote.getIdTipoProyecto()), "TipoProyecto"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(lote.getIdUbicacion()), "Ubicacion"));
        dto.setContrato(obtenerTexto(tipoContratoRepository.findById(lote.getIdTipoContrato()), "TipoContrato"));


    linderoRepository.findByIdLote(lote.getIdLote())
            .map(this::convertirALinderoDTO)
            .ifPresent(dto::setLindero);

    List<CuotaExtraordinariaDTO> cuotaextraordinaria = cuotaExtraordinariaRepository.findByIdLote(lote.getIdLote()).stream()
            .map(this::convertirACuotaExtraordinariaDTO)
            .collect(Collectors.toList());
    dto.setCuotasExtraordinarias(cuotaextraordinaria);

    cuotaRepository.findByIdLote(lote.getIdLote())
            .map(this::convertirACuotaDTO)
            .ifPresent(dto::setCuota);

    matrizRepository.findByIdLote(lote.getIdLote())
            .map(this::convertirAMatrizDTO)
            .ifPresent(dto::setMatriz);


        return dto;
    }

    public CuotaDTO  convertirACuotaDTO(Cuota cuota) {
            CuotaDTO dto = new CuotaDTO();
            dto.setIdLote(cuota.getIdLote());
            dto.setIdCuota(cuota.getIdCuota());
            dto.setLetrasPendientePago(cuota.getLetrasPendientePago());
            dto.setCuentaRecaudadora(cuota.getCuentaRecaudadora());
            dto.setCuotaInicialIncluyeSeparacionLetras(cuota.getCuotaInicialIncluyeSeparacionLetras());
            dto.setCuotaInicialIncluyeSeparacion(cuota.getCuotaInicialIncluyeSeparacion());
            dto.setMontoCuotas(cuota.getMontoCuotas());
            dto.setMontoCuotaLetras(cuota.getMontoCuotaLetras());
            dto.setFechaPago(cuota.getFechaPago());
            dto.setCuotaInicialBanco(cuota.getCuotaInicialBanco());
            dto.setCantidadCuotas(cuota.getCantidadCuotas());
            dto.setCantidadCuotaLetras(cuota.getCantidadCuotaLetras());
            dto.setCantidadCuotaCuentaRecaudadora(cuota.getCantidadCuotaCuentaRecaudadora());
            dto.setCantidadCuotaBanco(cuota.getCantidadCuotaBanco());
            dto.setCuotaPendientePago(cuota.getCuotaPendientePago());
            dto.setSaldoLote(cuota.getSaldoLote());
            dto.setSaldoLoteLetras(cuota.getSaldoLoteLetras());
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

    private MatrizDTO convertirAMatrizDTO(Matriz matriz) {
        MatrizDTO dto = new MatrizDTO();
        dto.setIdMatriz(matriz.getIdMatriz());
        dto.setIdLote(matriz.getIdLote());
        dto.setIdDepartamento(matriz.getIdDepartamento());
        dto.setIdProvincia(matriz.getIdProvincia());
        dto.setIdDistrito(matriz.getIdDistrito());
        dto.setUbicacion(matriz.getUbicacion());
        dto.setAreaMatrizHas(matriz.getAreaMatrizHas());
        dto.setRegistro(matriz.getRegistro());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastral(matriz.getUnidadCatastral());
        dto.setUrbanizacion(matriz.getUrbanizacion());
        dto.setCompraventa(matriz.getCompraventa());
        dto.setSituacionLegal(matriz.getSituacionLegal());
        return dto;
    }

    private CuotaExtraordinariaDTO convertirACuotaExtraordinariaDTO(CuotaExtraordinaria cuotaextraordinaria) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(cuotaextraordinaria.getIdCuotaExtraordinaria());
        dto.setIdLote(cuotaextraordinaria.getIdLote());
        dto.setCantidadCuotaExtraordinaria(cuotaextraordinaria.getCantidadCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinaria(cuotaextraordinaria.getMontoCuotaExtraordinaria());
        dto.setMediosPago(cuotaextraordinaria.getMediosPago());
        return dto;
    }


    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {
        List<Cliente> todosLosClientes = clienteRepository.findAll();

        // Buscar si ya existe alguien con ese número de identificación
        List<Cliente> existentes = todosLosClientes.stream()
                .filter(c -> c.getNumeroIdentificacion().equals(clienteDTO.getNumeroIdentificacion()))
                .collect(Collectors.toList());

        Cliente cliente = convertirAClienteEntidad(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);

        if (existentes.isEmpty()) {
            // No existe el grupo, buscar el mayor idClienteClone manualmente
            OptionalInt maxIdClienteClone = todosLosClientes.stream()
                    .mapToInt(Cliente::getIdClienteClone)
                    .max();

            int nuevoIdClone = maxIdClienteClone.orElse(clienteGuardado.getIdCliente() - 1) + 1;
            clienteGuardado.setIdClienteClone(nuevoIdClone);
        } else {
            // Ya existe el grupo, usar el idClienteClone del grupo existente
            int idPadre = existentes.stream()
                    .map(Cliente::getIdClienteClone)
                    .min(Integer::compareTo)
                    .orElseThrow();
            clienteGuardado.setIdClienteClone(idPadre);
        }

        clienteGuardado = clienteRepository.save(clienteGuardado);
        return convertirAClienteDTO(clienteGuardado);
    }



    public List<ClienteDTO> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::convertirAClienteDTO)
                .collect(Collectors.toList());
    }


    public LoteConClienteCompletoDTO obtenerClientePorId(int idCliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);

        if (clienteOptional.isEmpty()) {
            return null;
        }

        Cliente cliente = clienteOptional.get();

        ClienteDTO clienteDTO = convertirAClienteDTO(cliente);

        LoteDTO loteDTO = cliente.getLotes()
                .stream()
                .findFirst()
                .map(this::mapearLoteALoteDTO)
                .orElse(null);

        LoteConClienteCompletoDTO dto = new LoteConClienteCompletoDTO();
        dto.setCliente(clienteDTO);
        dto.setLote(loteDTO);

        return dto;
    }



    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertirAClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setIdClienteClone(cliente.getIdClienteClone());
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
        dto.setDescripcionEstadoCivil(cliente.getDescripcionEstadoCivil());

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

    private CopropietarioDTO convertirACopropietarioDTO(Copropietario copropietario) {
        CopropietarioDTO dto = new CopropietarioDTO();
        dto.setIdCopropietario(copropietario.getIdCopropietario());
        dto.setIdClienteCopropietarios(copropietario.getIdClienteCopropietarios());
        dto.setIdResidenciaCopropietarios(copropietario.getIdResidenciaCopropietarios());
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
        dto.setNumeroIdentificacionCopropietarios(copropietario.getNumeroIdentificacionCopropietarios());

        dto.setOperarioCopropietarios(obtenerTexto(operarioRepository.findById(copropietario.getIdOperarioCopropietarios()), "TipoOperario"));
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
        cliente.setIdClienteClone(dto.getIdClienteClone());
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
        cliente.setDescripcionEstadoCivil(dto.getDescripcionEstadoCivil());
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
