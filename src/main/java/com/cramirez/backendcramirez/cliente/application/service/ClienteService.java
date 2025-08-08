package com.cramirez.backendcramirez.cliente.application.service;
import com.cramirez.backendcramirez.auth.domain.entity.Credenciales;
import com.cramirez.backendcramirez.auth.infrastructure.repository.CredencialesRepository;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.domain.entity.ClienteConyuge;
import com.cramirez.backendcramirez.cliente.dto.*;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteConyugeRepository;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import com.cramirez.backendcramirez.copropietario.domain.entity.Copropietario;
import com.cramirez.backendcramirez.copropietario.domain.entity.CopropietarioConyuge;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioConyugeDTO;
import com.cramirez.backendcramirez.copropietario.dto.CopropietarioDTO;
import com.cramirez.backendcramirez.copropietario.infrastructure.repository.CopropietarioConyugeRepository;
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
import com.cramirez.backendcramirez.operario.application.service.OperarioService;
import com.cramirez.backendcramirez.operario.domain.entity.Operario;
import com.cramirez.backendcramirez.operario.dto.OperarioDTO;
import com.cramirez.backendcramirez.operario.infrastructure.repository.OperarioRepository;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.TipoProyectoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
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
    private final CopropietarioConyugeRepository copropietarioConyugeRepository;
    private final OperarioService operarioService;


    @Autowired
    public ClienteService(ClienteRepository clienteRepository, LoteRepository loteRepository, CredencialesRepository credencialesRepository, CopropietarioRepository copropietarioRepository, OperarioRepository operarioRepository, PrefijotelefonicoRepository prefijotelefonicoRepository, IdentificacionRepository identificacionRepository, EstadoCivilRepository estadoCivilRepository, NacionalidadRepository nacionalidadRepository, ResidenciaRepository residenciaRepository, DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository, TipoProyectoRepository tipoProyectoRepository, UbicacionRepository ubicacionRepository, TipoContratoRepository tipoContratoRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository, MatrizRepository matrizRepository, ClienteConyugeRepository clienteConyugeRepository, CuotaRepository cuotaRepository, CopropietarioConyugeRepository copropietarioConyugeRepository, OperarioService operarioService) {
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
        this.copropietarioConyugeRepository = copropietarioConyugeRepository;
        this.operarioService = operarioService;
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

        if (loteConClienteCompletoDTO.getCliente().getCopropietarioconyuge() != null) {
            for (CopropietarioConyugeDTO copropietarioConyugeDTO : loteConClienteCompletoDTO.getCliente().getCopropietarioconyuge()) {
                editarCopropietarioConyuge(copropietarioConyugeDTO);
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

    private void editarCopropietarioConyuge(CopropietarioConyugeDTO copropietarioConyugeDTO) {
        Optional<CopropietarioConyuge> copropietarioConyugeOpt = copropietarioConyugeRepository.findById(copropietarioConyugeDTO.getIdCopropietarioConyuge());
        if (copropietarioConyugeOpt.isPresent()) {
            CopropietarioConyuge copropietarioConyuge = copropietarioConyugeOpt.get();

            copropietarioConyuge.setOcupacionCopropietarioConyuge(copropietarioConyugeDTO.getOcupacionCopropietarioConyuge());
            copropietarioConyuge.setIdResidenciaCopropietarioConyuge(copropietarioConyugeDTO.getIdResidenciaCopropietarioConyuge());
            copropietarioConyuge.setIdOperarioCopropietarioConyuge(copropietarioConyugeDTO.getIdOperarioCopropietarioConyuge());
            copropietarioConyuge.setIdDistritoCopropietarioConyuge(copropietarioConyugeDTO.getIdDistritoCopropietarioConyuge());
            copropietarioConyuge.setIdProvinciaCopropietarioConyuge(copropietarioConyugeDTO.getIdProvinciaCopropietarioConyuge());
            copropietarioConyuge.setIdDepartamentoCopropietarioConyuge(copropietarioConyugeDTO.getIdDepartamentoCopropietarioConyuge());
            copropietarioConyuge.setIdNacionalidadCopropietarioConyuge(copropietarioConyugeDTO.getIdNacionalidadCopropietarioConyuge());
            copropietarioConyuge.setIdIdentificacionCopropietarioConyuge(copropietarioConyugeDTO.getIdIdentificacionCopropietarioConyuge());
            copropietarioConyuge.setNombresApellidosCopropietarioConyuge(copropietarioConyugeDTO.getNombresApellidosCopropietarioConyuge());
            copropietarioConyuge.setDireccionCopropietarioConyuge(copropietarioConyugeDTO.getDireccionCopropietarioConyuge());
            copropietarioConyuge.setNumeroIdentificacionCopropietarioConyuge(copropietarioConyugeDTO.getNumeroIdentificacionCopropietarioConyuge());

            copropietarioConyugeRepository.save(copropietarioConyuge);
        }
    }




    private void editarLote(LoteDTO loteDTO) {
        Optional<Lote> loteOpt = loteRepository.findById(loteDTO.getIdLote());
        if (loteOpt.isPresent()) {
            Lote lote = loteOpt.get();
            lote.setIdTipoContrato(loteDTO.getIdTipoContrato());
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
            lote.setFechaEntrega(loteDTO.getFechaEntrega());

            loteRepository.save(lote);

            if (loteDTO.getLindero() != null) {
                editarLindero(loteDTO.getLindero());
            }

            if (loteDTO.getCuota() != null) {
                editarCuota(loteDTO.getCuota());
            }

            if (loteDTO.getMatriz() != null) {
                editarMatriz(loteDTO.getMatriz());
            }

            if (loteDTO.getCuotaextraordinaria() != null) {
                editarCuotaExtraordinaria(loteDTO.getCuotaextraordinaria());
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
            lindero.setDescripcionPorLaDerecha(linderoDTO.getDescripcionPorLaDerecha());
            lindero.setDescripcionPorLaIzquierda(linderoDTO.getDescripcionPorLaIzquierda());
            lindero.setDescripcionPorElFrente(linderoDTO.getDescripcionPorElFrente());
            lindero.setDescripcionPorElFondo(linderoDTO.getDescripcionPorElFondo());

            linderoRepository.save(lindero);
        }
    }

    private void editarMatriz(MatrizDTO matrizDTO) {
        Optional<Matriz> matrizOpt = matrizRepository.findById(matrizDTO.getIdMatriz());
        if (matrizOpt.isPresent()) {
            Matriz matriz = matrizOpt.get();
            matriz.setIdDepartamentoMatriz(matrizDTO.getIdDepartamentoMatriz());
            matriz.setIdProvinciaMatriz(matrizDTO.getIdProvinciaMatriz());
            matriz.setIdDistritoMatriz(matrizDTO.getIdDistritoMatriz());
            matriz.setIdUbicacion(matrizDTO.getIdUbicacion());
            matriz.setAreaMatrizHasMatriz(matrizDTO.getAreaMatrizHasMatriz());
            matriz.setRegistroMatriz(matrizDTO.getRegistroMatriz());
            matriz.setPartidaMatriz(matrizDTO.getPartidaMatriz());
            matriz.setUnidadCatastralMatriz(matrizDTO.getUnidadCatastralMatriz());
            matriz.setUrbanizacionMatriz(matrizDTO.getUrbanizacionMatriz());
            matriz.setCompraventaMatriz(matrizDTO.getCompraventaMatriz());
            matriz.setSituacionLegalMatriz(matrizDTO.getSituacionLegalMatriz());
            matrizRepository.save(matriz);
        }
    }

    private void editarCuota(CuotaDTO cuotaDTO) {
        Optional<Cuota> cuotaOpt = cuotaRepository.findById(cuotaDTO.getIdCuota());
        if (cuotaOpt.isPresent()) {
            Cuota cuota = cuotaOpt.get();
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
            cuota.setMediosPago(cuotaDTO.getMediosPago());
            cuota.setEstadoCuenta(cuotaDTO.getEstadoCuenta());
            cuota.setMontoDeudaLetra(cuotaDTO.getMontoDeudaLetra());
            cuotaRepository.save(cuota);
        }
    }

    private void editarCuotaExtraordinaria(CuotaExtraordinariaDTO cuotaExtraordinariaDTO) {
        Optional<CuotaExtraordinaria> cuotaOpt = cuotaExtraordinariaRepository.findById(cuotaExtraordinariaDTO.getIdCuotaExtraordinaria());
        if (cuotaOpt.isPresent()) {
            CuotaExtraordinaria cuotaExtraordinaria = cuotaOpt.get();
            cuotaExtraordinaria.setCantidadCuotaExtraordinaria(cuotaExtraordinariaDTO.getCantidadCuotaExtraordinaria());
            cuotaExtraordinaria.setMontoCuotaExtraordinaria(cuotaExtraordinariaDTO.getMontoCuotaExtraordinaria());
            cuotaExtraordinariaRepository.save(cuotaExtraordinaria);
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
            if (cliente == null || cliente.getLotes() == null) continue;

            for (Lote lote : cliente.getLotes()) {
                if (lote == null) continue;
                LoteDTO loteDTO = mapearLoteALoteDTO(lote);
                if (loteDTO.getLindero() == null || loteDTO.getCuota() == null || loteDTO.getMatriz() == null) {
                    continue;
                }

                LoteConClienteCompletoDTO dto = new LoteConClienteCompletoDTO();
                dto.setCliente(convertirAClienteDTO(cliente));
                dto.setLote(loteDTO);
                resultado.add(dto);
            }
        }
        return resultado;
    }



    public List<LoteConClienteCompletoDTO> obtenerClientesConLotesPorOperario(int idOperario) {
        List<Cliente> clientes = clienteRepository.findByIdOperario(idOperario);
        List<LoteConClienteCompletoDTO> resultado = new ArrayList<>();

        for (Cliente cliente : clientes) {
            if (cliente == null || cliente.getLotes() == null) continue;

            for (Lote lote : cliente.getLotes()) {
                if (lote == null) continue;

                LoteDTO loteDTO = mapearLoteALoteDTO(lote);
                if (loteDTO == null) continue;

                if (loteDTO.getLindero() == null || loteDTO.getCuota() == null || loteDTO.getMatriz() == null) continue;

                LoteConClienteCompletoDTO dto = new LoteConClienteCompletoDTO();
                dto.setCliente(convertirAClienteDTO(cliente));
                dto.setLote(loteDTO);
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
        dto.setFechaEntrega(lote.getFechaEntrega());
        dto.setAlicuota(lote.getAlicuota());
        dto.setAlicuotaLetras(lote.getAlicuotaLetras());

        dto.setTipoProyecto(obtenerTexto(tipoProyectoRepository.findById(lote.getIdTipoProyecto()), "TipoProyecto"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(lote.getIdUbicacion()), "Ubicacion"));
        dto.setContrato(obtenerTexto(tipoContratoRepository.findById(lote.getIdTipoContrato()), "TipoContrato"));


    linderoRepository.findByIdLote(lote.getIdLote())
            .map(this::convertirALinderoDTO)
            .ifPresent(dto::setLindero);

    cuotaExtraordinariaRepository.findByIdLote(lote.getIdLote())
            .map(this::convertirACuotaExtraordinariaDTO)
            .ifPresent(dto::setCuotaextraordinaria);

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
        dto.setMediosPago(cuota.getMediosPago());
        dto.setEstadoCuenta(cuota.getEstadoCuenta());
        dto.setMontoDeudaLetra(cuota.getMontoDeudaLetra());
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
        dto.setDescripcionPorLaDerecha(lindero.getDescripcionPorLaDerecha());
        dto.setDescripcionPorLaIzquierda(lindero.getDescripcionPorLaIzquierda());
        dto.setDescripcionPorElFrente(lindero.getDescripcionPorElFrente());
        dto.setDescripcionPorElFondo(lindero.getDescripcionPorElFondo());

        return dto;
    }

    private MatrizDTO convertirAMatrizDTO(Matriz matriz) {
        MatrizDTO dto = new MatrizDTO();
        dto.setIdMatriz(matriz.getIdMatriz());
        dto.setIdLote(matriz.getIdLote());
        dto.setIdDepartamentoMatriz(matriz.getIdDepartamentoMatriz());
        dto.setIdProvinciaMatriz(matriz.getIdProvinciaMatriz());
        dto.setIdDistritoMatriz(matriz.getIdDistritoMatriz());
        dto.setIdUbicacion(matriz.getIdUbicacion());
        dto.setAreaMatrizHasMatriz(matriz.getAreaMatrizHasMatriz());
        dto.setRegistroMatriz(matriz.getRegistroMatriz());
        dto.setPartidaMatriz(matriz.getPartidaMatriz());
        dto.setUnidadCatastralMatriz(matriz.getUnidadCatastralMatriz());
        dto.setUrbanizacionMatriz(matriz.getUrbanizacionMatriz());
        dto.setCompraventaMatriz(matriz.getCompraventaMatriz());
        dto.setSituacionLegalMatriz(matriz.getSituacionLegalMatriz());

        dto.setTxtdepartamentomatriz(obtenerTexto(departamentoRepository.findById(matriz.getIdDepartamentoMatriz()), "NombreDepartamento"));
        dto.setTxtprovinciamatriz(obtenerTexto(provinciaRepository.findById(matriz.getIdProvinciaMatriz()), "NombreProvincia"));
        dto.setTxtdistritomatriz(obtenerTexto(distritoRepository.findById(matriz.getIdDistritoMatriz()), "NombreDistrito"));
        dto.setTxtubicacionmatriz(obtenerTexto(ubicacionRepository.findById(matriz.getIdUbicacion()), "Ubicacion"));

        return dto;
    }

    private CuotaExtraordinariaDTO convertirACuotaExtraordinariaDTO(CuotaExtraordinaria cuotaextraordinaria) {
        CuotaExtraordinariaDTO dto = new CuotaExtraordinariaDTO();
        dto.setIdCuotaExtraordinaria(cuotaextraordinaria.getIdCuotaExtraordinaria());
        dto.setIdLote(cuotaextraordinaria.getIdLote());
        dto.setCantidadCuotaExtraordinaria(cuotaextraordinaria.getCantidadCuotaExtraordinaria());
        dto.setCantidadCuotaExtraordinariaLetras(cuotaextraordinaria.getCantidadCuotaExtraordinariaLetras());
        dto.setMontoCuotaExtraordinaria(cuotaextraordinaria.getMontoCuotaExtraordinaria());
        dto.setMontoCuotaExtraordinariaLetras(cuotaextraordinaria.getMontoCuotaExtraordinariaLetras());

        return dto;
    }


    public ClienteDTO guardarCliente(ClienteDTO clienteDTO) {
        List<Cliente> todosLosClientes = clienteRepository.findAll();

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

        List<CopropietarioConyugeDTO> copropietarioconyuge = copropietarioConyugeRepository.findByIdClienteCopropietarioConyuge (cliente.getIdCliente())
                .stream()
                .map(this::convertirACopropietarioConyugeDTO)
                .collect(Collectors.toList());
        dto.setCopropietarioconyuge(copropietarioconyuge);

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

    private CopropietarioConyugeDTO convertirACopropietarioConyugeDTO(CopropietarioConyuge copropietarioConyuge) {
        CopropietarioConyugeDTO dto = new CopropietarioConyugeDTO();
        dto.setIdCopropietarioConyuge(copropietarioConyuge.getIdCopropietarioConyuge());
        dto.setIdClienteCopropietarioConyuge(copropietarioConyuge.getIdClienteCopropietarioConyuge());
        dto.setOcupacionCopropietarioConyuge(copropietarioConyuge.getOcupacionCopropietarioConyuge());
        dto.setIdResidenciaCopropietarioConyuge(copropietarioConyuge.getIdResidenciaCopropietarioConyuge());
        dto.setIdOperarioCopropietarioConyuge(copropietarioConyuge.getIdOperarioCopropietarioConyuge());
        dto.setIdDistritoCopropietarioConyuge(copropietarioConyuge.getIdDistritoCopropietarioConyuge());
        dto.setIdProvinciaCopropietarioConyuge(copropietarioConyuge.getIdProvinciaCopropietarioConyuge());
        dto.setIdDepartamentoCopropietarioConyuge(copropietarioConyuge.getIdDepartamentoCopropietarioConyuge());
        dto.setIdNacionalidadCopropietarioConyuge(copropietarioConyuge.getIdNacionalidadCopropietarioConyuge());
        dto.setIdIdentificacionCopropietarioConyuge(copropietarioConyuge.getIdIdentificacionCopropietarioConyuge());
        dto.setNombresApellidosCopropietarioConyuge(copropietarioConyuge.getNombresApellidosCopropietarioConyuge());
        dto.setDireccionCopropietarioConyuge(copropietarioConyuge.getDireccionCopropietarioConyuge());
        dto.setNumeroIdentificacionCopropietarioConyuge(copropietarioConyuge.getNumeroIdentificacionCopropietarioConyuge());

        dto.setOperarioCopropietarioConyuge(obtenerTexto(operarioRepository.findById(copropietarioConyuge.getIdOperarioCopropietarioConyuge()), "TipoOperario"));
        dto.setDocumentoIdentificacionCopropietarioConyuge(obtenerTexto(identificacionRepository.findById(copropietarioConyuge.getIdIdentificacionCopropietarioConyuge()), "DocumentoIdentificacion"));
        dto.setNacionalidadCopropietarioConyuge(obtenerTexto(nacionalidadRepository.findById(copropietarioConyuge.getIdNacionalidadCopropietarioConyuge()), "NombreNacionalidad"));
        dto.setResidenciaCopropietarioConyuge(obtenerTexto(residenciaRepository.findById(copropietarioConyuge.getIdResidenciaCopropietarioConyuge()), "Residencia"));
        dto.setDepartamentoCopropietarioConyuge(obtenerTexto(departamentoRepository.findById(copropietarioConyuge.getIdDepartamentoCopropietarioConyuge()), "NombreDepartamento"));
        dto.setProvinciaCopropietarioConyuge(obtenerTexto(provinciaRepository.findById(copropietarioConyuge.getIdProvinciaCopropietarioConyuge()), "NombreProvincia"));
        dto.setDistritoCopropietarioConyuge(obtenerTexto(distritoRepository.findById(copropietarioConyuge.getIdDistritoCopropietarioConyuge()), "NombreDistrito"));


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

    //---------Indicadores-----------------
    //conteo de clientes
    public int contarClientesRegistrados() {
        List<LoteConClienteCompletoDTO> clientes = obtenerClientesConLotes();

        LocalDate hoy = LocalDate.now();

        long count = clientes.stream()
                .filter(c -> {
                    if (c.getCliente() == null || c.getLote() == null) return false;
                    if (c.getLote().getLindero() == null || c.getLote().getCuota() == null || c.getLote().getMatriz() == null) return false;

                    LocalDate fechaRegistro = c.getCliente().getFechaRegistro().toLocalDate(); // si es LocalDateTime
                    return fechaRegistro.equals(hoy);
                })
                .count();

        return (int) count;
    }

    public int TotalClientesRegistrados() {
        List<LoteConClienteCompletoDTO> clientes = obtenerClientesConLotes();

        long count = clientes.stream()
                .filter(c -> {
                    if (c.getCliente() == null || c.getLote() == null) return false;
                    if (c.getLote().getLindero() == null || c.getLote().getCuota() == null || c.getLote().getMatriz() == null) return false;

                    return c.getCliente().getFechaRegistro() != null; // Basta con que esté registrado
                })
                .count();

        return (int) count;
    }


    //indicador de rendimiento mensual
    public List<Map<String, Object>> contarClientesRegistradosPorTipoOperarioDelMes() {
        List<LoteConClienteCompletoDTO> clientes = obtenerClientesConLotes();

        Map<Integer, String> idToTipo = operarioService.obtenerTodosLosOperarios().stream()
                .collect(Collectors.toMap(
                        OperarioDTO::getIdOperario,
                        OperarioDTO::getTipoOperario
                ));

        LocalDate primerDia = LocalDate.now().withDayOfMonth(1);
        LocalDate ultimoDia = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        Map<Integer, Long> conteoPorId = clientes.stream()
                .filter(c -> c.getCliente() != null
                        && c.getLote() != null
                        && c.getLote().getLindero() != null
                        && c.getLote().getCuota() != null
                        && c.getLote().getMatriz() != null
                        && c.getCliente().getFechaRegistro() != null)
                .filter(c -> {
                    LocalDate fecha = c.getCliente().getFechaRegistro().toLocalDate();
                    return !fecha.isBefore(primerDia) && !fecha.isAfter(ultimoDia);
                })
                .map(c -> c.getCliente().getIdOperario())
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));

        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : idToTipo.entrySet()) {
            Integer idOperario = entry.getKey();
            String tipoOperario = entry.getValue();
            Long cantidad = conteoPorId.getOrDefault(idOperario, 0L);

            Map<String, Object> item = new HashMap<>();
            item.put("tipoOperario", tipoOperario);
            item.put("cantidad", cantidad.intValue());
            resultado.add(item);
        }

        return resultado;
    }



    /*
    public int obtenerTiempoPromedioPorCliente() {
        List<Cliente> clientes = clienteRepository.findAll();

        List<Long> minutosPorCliente = clientes.stream()
                .filter(c -> c.getFechaRegistro() != null)
                .map(c -> {
                    // Aquí asumimos que fechaRegistro es LocalDateTime
                    Instant fechaRegistro = c.getFechaRegistro().atZone(ZoneId.systemDefault()).toInstant();
                    Instant ahora = Instant.now();
                    return Duration.between(fechaRegistro, ahora).toMinutes();
                })
                .collect(Collectors.toList());

        if (minutosPorCliente.isEmpty()) return 0;

        long suma = minutosPorCliente.stream().mapToLong(Long::longValue).sum();
        return (int) (suma / minutosPorCliente.size());
    }

     */

}
