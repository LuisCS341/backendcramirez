package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.cuotaExtraordinaria.domain.entity.CuotaExtraordinaria;
import com.cramirez.backendcramirez.cuotaExtraordinaria.dto.CuotaExtraordinariaDTO;
import com.cramirez.backendcramirez.cuotaExtraordinaria.infrastructure.repository.CuotaExtraordinariaRepository;
import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
import com.cramirez.backendcramirez.lote.domain.entity.Lindero;
import com.cramirez.backendcramirez.lote.domain.entity.Lote;
import com.cramirez.backendcramirez.lote.dto.LinderoDTO;
import com.cramirez.backendcramirez.lote.dto.LoteDTO;
import com.cramirez.backendcramirez.lote.infrastructure.repository.LinderoRepository;
import com.cramirez.backendcramirez.lote.infrastructure.repository.LoteRepository;
import com.cramirez.backendcramirez.matriz.domain.entity.Matriz;
import com.cramirez.backendcramirez.matriz.dto.MatrizDTO;
import com.cramirez.backendcramirez.matriz.infrastructure.repository.MatrizRepository;
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
        lote.setIdTipoProyecto(dto.getIdTipoProyecto());
        lote.setIdUbicacion(dto.getIdUbicacion());
        lote.setManzana(dto.getManzana());
        lote.setNumeroLote(dto.getNumeroLote());
        lote.setIdTipoContrato(dto.getIdTipoContrato());
        lote.setAreaLote(dto.getAreaLote());
        lote.setAreaLoteLetras(dto.getAreaLoteLetras());
        lote.setCostoLote(dto.getCostoLote());
        lote.setMontoLetras(dto.getMontoLetras());
        lote.setPagoInicial(dto.getPagoInicial());
        lote.setSeparacion(dto.getSeparacion());
        lote.setMontoCuotas(dto.getMontoCuotas());
        lote.setCantidadCuotas(dto.getCantidadCuotas());
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
        lote.setAreaMatrizHas(dto.getAreaMatrizHas());
        lote.setRegistroDe(dto.getRegistroDe());
        lote.setPartidaMatriz(dto.getPartidaMatriz());
        lote.setUbicacionLote(dto.getUbicacionLote());
        lote.setUnidadCatastralMatriz(dto.getUnidadCatastralMatriz());
        lote.setUrbanizacionMatriz(dto.getUrbanizacionMatriz());
        lote.setDistritoMatriz(dto.getDistritoMatriz());
        lote.setProvinciaMatriz(dto.getProvinciaMatriz());
        lote.setDepartamentoMatriz(dto.getDepartamentoMatriz());
        lote.setCompraVentaMatriz(dto.getCompraVentaMatriz());
        lote.setSituacionLegalMatriz(dto.getSituacionLegalMatriz());
        return lote;
    }

    private LoteDTO convertToDTO(Lote lote) {
        LoteDTO dto = new LoteDTO();
        dto.setIdLote(lote.getIdLote());
        dto.setIdClienteLote(lote.getIdClienteLote());
        dto.setIdOperario(lote.getIdOperario());
        dto.setIdTipoProyecto(lote.getIdTipoProyecto());
        dto.setIdUbicacion(lote.getIdUbicacion());
        dto.setManzana(lote.getManzana());
        dto.setNumeroLote(lote.getNumeroLote());
        dto.setIdTipoContrato(lote.getIdTipoContrato());
        dto.setAreaLote(lote.getAreaLote());
        dto.setAreaLoteLetras(lote.getAreaLoteLetras());
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
        return dto;
    }
}
