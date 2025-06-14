package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.lote.domain.entity.CuotaExtraordinaria;
import com.cramirez.backendcramirez.lote.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.CuotaExtraordinariaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import com.cramirez.backendcramirez.lote.domain.entity.Lote;
import com.cramirez.backendcramirez.lote.dto.LinderoDTO;
import com.cramirez.backendcramirez.lote.dto.LoteDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.LinderoRepository;
import com.cramirez.backendcramirez.lote.infrastructure.repository.LoteRepository;
import com.cramirez.backendcramirez.lote.domain.entity.Matriz;
import com.cramirez.backendcramirez.lote.dto.MatrizDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.MatrizRepository;
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
    private final TipoProyectoRepository tipoProyectoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final TipoContratoRepository tipoContratoRepository;
    private final MatrizRepository matrizRepository;
    private final CuotaExtraordinariaRepository cuotaExtraordinariaRepository;
    private final LinderoRepository linderoRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository, TipoContratoRepository tipoContratoRepository, UbicacionRepository ubicacionRepository, TipoProyectoRepository tipoProyectoRepository, MatrizRepository matrizRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository) {
        this.loteRepository = loteRepository;
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.tipoContratoRepository = tipoContratoRepository;
        this.matrizRepository = matrizRepository;
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.linderoRepository = linderoRepository;
    }

    public List<LoteDTO> getAllLotes() {
        List<Lote> lotes = loteRepository.findAll();
        return lotes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<LoteDTO> getLoteById(Integer id) {
        return loteRepository.findById(id).map(this::convertToDTO);
    }

    public LoteDTO saveLote(LoteDTO loteDTO) {

        int cantidadLotes = loteRepository.countByClienteIdCliente(loteDTO.getIdClienteLote());


        int siguienteNumero = cantidadLotes + 1;


        String codigo = loteDTO.getIdClienteLote() + "-" + siguienteNumero;
        loteDTO.setCodigoLoteCliente(codigo);

        Lote lote = convertToEntity(loteDTO);

        Lote savedLote = loteRepository.save(lote);

        return convertToDTO(savedLote);
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
        lote.setIdTipoProyecto(dto.getIdTipoProyecto());
        lote.setIdTipoContrato(dto.getIdTipoContrato());
        lote.setIdUbicacion(dto.getIdUbicacion());
        lote.setManzana(dto.getManzana());
        lote.setNumeroLote(dto.getNumeroLote());
        lote.setAreaLote(dto.getAreaLote());
        lote.setAreaLoteLetras(dto.getAreaLoteLetras());
        lote.setCostoLote(dto.getCostoLote());
        lote.setCostoLoteLetras(dto.getCostoLoteLetras());
        lote.setMontoCuotas(dto.getMontoCuotas());
        lote.setMontoCuotaLetras(dto.getMontoCuotaLetras());
        lote.setCantidadCuotas(dto.getCantidadCuotas());
        lote.setCantidadCuotaLetras(dto.getCantidadCuotaLetras());
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
        lote.setCantidadCuotaCuentaRecaudadora(dto.getCantidadCuotaCuentaRecaudadora());
        lote.setSaldoLote(dto.getSaldoLote());
        lote.setSaldoLoteLetras(dto.getSaldoLoteLetras());
        lote.setCuentaRecaudadora(dto.getCuentaRecaudadora());
        lote.setCuotaInicialBanco(dto.getCuotaInicialBanco());
        lote.setCantidadCuotaBanco(dto.getCantidadCuotaBanco());
        lote.setFechaPago(dto.getFechaPago());
        lote.setCuotaInicialIncluyeSeparacion(dto.getCuotaInicialIncluyeSeparacion());
        lote.setCuotaInicialIncluyeSeparacionLetras(dto.getCuotaInicialIncluyeSeparacionLetras());
        lote.setPrecioMetroCuadrado(dto.getPrecioMetroCuadrado());
        lote.setPrecioMetroCuadradoLetras(dto.getPrecioMetroCuadradoLetras());
        lote.setTipoRepresentante(dto.getTipoRepresentante());
        return lote;
    }

    private LoteDTO convertToDTO(Lote lote) {
        LoteDTO dto = new LoteDTO();
        dto.setIdLote(lote.getIdLote());
        dto.setIdOperario(lote.getIdOperario());
        dto.setIdClienteLote(lote.getIdClienteLote());
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
        dto.setMontoCuotas(lote.getMontoCuotas());
        dto.setMontoCuotaLetras(lote.getMontoCuotaLetras());
        dto.setCantidadCuotas(lote.getCantidadCuotas());
        dto.setCantidadCuotaLetras(lote.getCantidadCuotaLetras());
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
        dto.setSaldoLote(lote.getSaldoLote());
        dto.setSaldoLoteLetras(lote.getSaldoLoteLetras());
        dto.setCuentaRecaudadora(lote.getCuentaRecaudadora());
        dto.setCuotaInicialBanco(lote.getCuotaInicialBanco());
        dto.setCantidadCuotaBanco(lote.getCantidadCuotaBanco());
        dto.setCantidadCuotaCuentaRecaudadora(lote.getCantidadCuotaCuentaRecaudadora());
        dto.setFechaPago(lote.getFechaPago());
        dto.setCuotaInicialIncluyeSeparacion(lote.getCuotaInicialIncluyeSeparacion());
        dto.setCuotaInicialIncluyeSeparacionLetras(lote.getCuotaInicialIncluyeSeparacionLetras());
        dto.setPrecioMetroCuadrado(lote.getPrecioMetroCuadrado());
        dto.setPrecioMetroCuadradoLetras(lote.getPrecioMetroCuadradoLetras());
        dto.setTipoRepresentante(lote.getTipoRepresentante());

        dto.setTipoProyecto(obtenerTexto(tipoProyectoRepository.findById(lote.getIdTipoProyecto()), "TipoProyecto"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(lote.getIdUbicacion()), "Ubicacion"));
        dto.setContrato(obtenerTexto(tipoContratoRepository.findById(lote.getIdTipoContrato()), "TipoContrato"));


        linderoRepository.findByIdLote(lote.getIdLote())
                .map(this::convertirALinderoDTO)
                .ifPresent(dto::setLindero);

        List<CuotaExtraordinaria> cuotas = cuotaExtraordinariaRepository.findByIdLote(lote.getIdLote());
        List<CuotaExtraordinariaDTO> cuotaDTOs = cuotas.stream()
                .map(this::convertirACuotaDTO)
                .collect(Collectors.toList());
        dto.setCuotasExtraordinarias(cuotaDTOs);

        List<Matriz> matrices = matrizRepository.findByIdLote(lote.getIdLote());
        List<MatrizDTO> matrizDTOs = matrices.stream()
                .map(this::convertirAMatrizDTO)
                .collect(Collectors.toList());
        dto.setMatriz(matrizDTOs);

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
        return dto;
    }
}
