package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
import com.cramirez.backendcramirez.lote.domain.entity.*;
import com.cramirez.backendcramirez.lote.dto.*;
import com.cramirez.backendcramirez.lote.infrastructure.repository.*;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import com.cramirez.backendcramirez.metadata.infrastructure.repository.TipoContratoRepository;
import com.cramirez.backendcramirez.proyecto.infrastructure.repository.TipoProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final ClienteRepository clienteRepository;
    private final TipoProyectoRepository tipoProyectoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final TipoContratoRepository tipoContratoRepository;
    private final CuotaExtraordinariaRepository cuotaExtraordinariaRepository;
    private final LinderoRepository linderoRepository;
    private final CuotaRepository cuotaRepository;
    private final MatrizRepository matrizRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository, ClienteRepository clienteRepository, TipoContratoRepository tipoContratoRepository, UbicacionRepository ubicacionRepository, TipoProyectoRepository tipoProyectoRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository, CuotaRepository cuotaRepository, MatrizRepository matrizRepository) {
        this.loteRepository = loteRepository;
        this.clienteRepository = clienteRepository;
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.tipoContratoRepository = tipoContratoRepository;
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.linderoRepository = linderoRepository;
        this.cuotaRepository = cuotaRepository;
        this.matrizRepository = matrizRepository;
    }

    public List<LoteDTO> getAllLotes() {
        List<Lote> lotes = loteRepository.findAll();
        return lotes.stream().map(this::convertToLoteDTO).collect(Collectors.toList());
    }

    public Optional<LoteDTO> getLoteById(Integer id) {
        return loteRepository.findById(id).map(this::convertToLoteDTO);
    }

    public LoteDTO saveLote(LoteDTO loteDTO) {

        Cliente cliente = clienteRepository.findById(loteDTO.getIdClienteLote())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        loteDTO.setIdClienteClone(cliente.getIdClienteClone());

        List<Lote> lotes = loteRepository.findByClienteIdClienteClone(loteDTO.getIdClienteClone());
        long cantidadLotesValidos = lotes.stream()
                .filter(l -> l.getCodigoLoteCliente() != null && !l.getCodigoLoteCliente().isBlank())
                .count();

        long siguienteNumero = cantidadLotesValidos + 1;
        String codigo = loteDTO.getIdClienteClone() + "-" + siguienteNumero;
        loteDTO.setCodigoLoteCliente(codigo);

        Lote lote = convertToEntity(loteDTO);
        Lote savedLote = loteRepository.save(lote);

        return convertToLoteDTO(savedLote);
    }


    public void deleteLote(Integer id) {
        loteRepository.deleteById(id);
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

    private Lote convertToEntity(LoteDTO dto) {
        Lote lote = new Lote();
        lote.setIdOperario(dto.getIdOperario());
        lote.setIdClienteLote(dto.getIdClienteLote());
        lote.setCodigoLoteCliente(dto.getCodigoLoteCliente());
        lote.setIdClienteClone(dto.getIdClienteClone());
        lote.setIdTipoProyecto(dto.getIdTipoProyecto());
        lote.setIdTipoContrato(dto.getIdTipoContrato());
        lote.setIdUbicacion(dto.getIdUbicacion());
        lote.setManzana(dto.getManzana());
        lote.setNumeroLote(dto.getNumeroLote());
        lote.setAreaLote(dto.getAreaLote());
        lote.setAreaLoteLetras(dto.getAreaLoteLetras());
        lote.setCostoLote(dto.getCostoLote());
        lote.setCostoLoteLetras(dto.getCostoLoteLetras());
        lote.setEmpresa(dto.getEmpresa());
        lote.setEmpresaVende(dto.getEmpresaVende());
        lote.setRucVendedor(dto.getRucVendedor());
        lote.setDireccionVendedor(dto.getDireccionVendedor());
        lote.setRepresentanteLegalVendedor(dto.getRepresentanteLegalVendedor());
        lote.setDniVendedor(dto.getDniVendedor());
        lote.setNumeroPartidaPoderVendedor(dto.getNumeroPartidaPoderVendedor());
        lote.setMoneda(dto.getMoneda());
        lote.setNumCuenta(dto.getNumCuenta());
        lote.setCci(dto.getCci());
        lote.setFechaSale(dto.getFechaSale());
        lote.setFechaFirmaContratoDefinitivo(dto.getFechaFirmaContratoDefinitivo());
        lote.setUbicacionLote(dto.getUbicacionLote());
        lote.setFechaInicioContrato(dto.getFechaInicioContrato());
        lote.setFechaCancelacionContrato(dto.getFechaCancelacionContrato());
        lote.setPrecioMetroCuadrado(dto.getPrecioMetroCuadrado());
        lote.setPrecioMetroCuadradoLetras(dto.getPrecioMetroCuadradoLetras());
        lote.setTipoRepresentante(dto.getTipoRepresentante());
        lote.setMantenimientoMensual(dto.getMantenimientoMensual());
        lote.setMantenimientoMensualLetras(dto.getMantenimientoMensualLetras());
        lote.setEstadoCuenta(dto.getEstadoCuenta());
        lote.setMontoDeudaLetra(dto.getMontoDeudaLetra());
        lote.setFechaEntrega(dto.getFechaEntrega());
        lote.setAlicuota(dto.getAlicuota());
        lote.setAlicuotaLetras(dto.getAlicuotaLetras());

        return lote;
    }

    private LoteDTO convertToLoteDTO(Lote lote) {
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

       matrizRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirAMatrizDTO)
                .ifPresent(dto::setMatriz);

        List<CuotaExtraordinaria> cuotas = cuotaExtraordinariaRepository.findByIdLote(lote.getIdLote());
        List<CuotaExtraordinariaDTO> cuotaDTOs = cuotas.stream()
                .map(this::convertirACuotaExtraordinariaDTO)
                .collect(Collectors.toList());
        dto.setCuotasExtraordinarias(cuotaDTOs);

        cuotaRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirACuotaDTO)
                .ifPresent(dto::setCuota);

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

    public CuotaDTO convertirACuotaDTO(Cuota cuota) {
        CuotaDTO dto = new CuotaDTO();
        dto.setIdLote(cuota.getIdLote());
        dto.setIdCuota(cuota.getIdCuota());
        dto.setLetrasPendientePago(cuota.getLetrasPendientePago());
        dto.setCuentaRecaudadora(cuota.getCuentaRecaudadora());
        dto.setCuotaInicialIncluyeSeparacion(cuota.getCuotaInicialIncluyeSeparacion());
        dto.setCuotaInicialIncluyeSeparacionLetras(cuota.getCuotaInicialIncluyeSeparacionLetras());
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
}
