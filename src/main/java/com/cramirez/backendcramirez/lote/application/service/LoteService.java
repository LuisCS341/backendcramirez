package com.cramirez.backendcramirez.lote.application.service;
import com.cramirez.backendcramirez.cliente.domain.entity.Cliente;
import com.cramirez.backendcramirez.cliente.infrastructure.repository.ClienteRepository;
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

    @Autowired
    public LoteService(LoteRepository loteRepository, ClienteRepository clienteRepository, TipoContratoRepository tipoContratoRepository, UbicacionRepository ubicacionRepository, TipoProyectoRepository tipoProyectoRepository, CuotaExtraordinariaRepository cuotaExtraordinariaRepository, LinderoRepository linderoRepository) {
        this.loteRepository = loteRepository;
        this.clienteRepository = clienteRepository;
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.tipoContratoRepository = tipoContratoRepository;
        this.cuotaExtraordinariaRepository = cuotaExtraordinariaRepository;
        this.linderoRepository = linderoRepository;
    }

    public List<LoteDTO> getAllLotes() {
        List<Lote> lotes = loteRepository.findAll();
        return lotes.stream().map(this::convertToLoteDTO).collect(Collectors.toList());
    }

    public Optional<LoteDTO> getLoteById(Integer id) {
        return loteRepository.findById(id).map(this::convertToLoteDTO);
    }

    public LoteDTO saveLote(LoteDTO loteDTO) {

        // 1. Buscar el cliente original para obtener su ID_ClienteClone
        Cliente cliente = clienteRepository.findById(loteDTO.getIdClienteLote())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // 2. Asignar el ID_ClienteClone desde el cliente real
        loteDTO.setIdClienteClone(cliente.getIdClienteClone());

        // 3. Obtener todos los lotes del cliente
        List<Lote> lotes = loteRepository.findByClienteIdClienteClone(loteDTO.getIdClienteClone());

        // 4. Filtrar solo los lotes que tienen un código válido (≠ null o vacío)
        long cantidadLotesValidos = lotes.stream()
                .filter(l -> l.getCodigoLoteCliente() != null && !l.getCodigoLoteCliente().isBlank())
                .count();

        // 5. Generar el código: siempre debe ser ID-1, ID-2, ...
        long siguienteNumero = cantidadLotesValidos + 1;
        String codigo = loteDTO.getIdClienteClone() + "-" + siguienteNumero;
        loteDTO.setCodigoLoteCliente(codigo);

        // 6. Guardar lote en base de datos
        Lote lote = convertToEntity(loteDTO);
        Lote savedLote = loteRepository.save(lote);

        // 7. Retornar el resultado
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
        lote.setCuotaPendientePago(dto.getCuotaPendientePago());
        lote.setFechaEntrega(dto.getFechaEntrega());

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
        dto.setCuotaPendientePago(lote.getCuotaPendientePago());
        dto.setFechaEntrega(lote.getFechaEntrega());

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
        dto.setMediosPago(cuota.getMediosPago());
        return dto;
    }
}
