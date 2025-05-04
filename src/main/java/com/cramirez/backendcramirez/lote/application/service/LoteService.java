package com.cramirez.backendcramirez.lote.application.service;

import com.cramirez.backendcramirez.dto.lote.LoteDTO;
import com.cramirez.backendcramirez.entity.lote.Lote;
import com.cramirez.backendcramirez.repository.localizacion.UbicacionRepository;
import com.cramirez.backendcramirez.repository.lote.LoteRepository;
import com.cramirez.backendcramirez.repository.metadata.TipoContratoRepository;
import com.cramirez.backendcramirez.repository.proyecto.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final ProyectoRepository proyectoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final TipoContratoRepository tipoContratoRepository;

    @Autowired
    public LoteService(LoteRepository loteRepository,TipoContratoRepository tipoContratoRepository,UbicacionRepository ubicacionRepository, ProyectoRepository proyectoRepository) {
        this.loteRepository = loteRepository;
        this.proyectoRepository = proyectoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.tipoContratoRepository = tipoContratoRepository;
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

    public Optional<LoteDTO> getUltimoLote() {
        return loteRepository.findTopByOrderByIdLoteDesc()
                .map(this::convertToDTO);
    }


    public void deleteLote(Integer id) {
        loteRepository.deleteById(id);
    }

    private LoteDTO convertToDTO(Lote lote) {
        LoteDTO dto = new LoteDTO();
        dto.setIdLote(lote.getIdLote());
        dto.setIdClienteLote(lote.getIdClienteLote());
        dto.setIdOperario(lote.getIdOperario());
        dto.setIdTipoProyecto(lote.getIdTipoProyecto());
        dto.setIdUbicacion(lote.getIdUbicacion());
        dto.setNumeroLote(lote.getNumeroLote());
        dto.setIdTipoContrato(lote.getIdTipoContrato());
        dto.setAreaLote(lote.getAreaLote());
        dto.setCostoLote(lote.getCostoLote());
        dto.setMontoLetras(lote.getMontoLetras());
        dto.setPagoInicial(lote.getPagoInicial());
        dto.setSeparacion(lote.getSeparacion());
        dto.setMontoCuotas(lote.getMontoCuotas());
        dto.setCatidadCuotas(lote.getCatidadCuotas());


        dto.setTipoProyecto(obtenerTexto(proyectoRepository.findById(lote.getIdTipoProyecto()), "TipoProyecto"));
        dto.setUbicacion(obtenerTexto(ubicacionRepository.findById(lote.getIdUbicacion()), "Ubicacion"));
        dto.setContrato(obtenerTexto(tipoContratoRepository.findById(lote.getIdTipoContrato()), "TipoContrato"));
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
        lote.setCostoLote(dto.getCostoLote());
        lote.setMontoLetras(dto.getMontoLetras());
        lote.setPagoInicial(dto.getPagoInicial());
        lote.setSeparacion(dto.getSeparacion());
        lote.setMontoCuotas(dto.getMontoCuotas());
        lote.setCatidadCuotas(dto.getCatidadCuotas());

        return lote;
    }
}
