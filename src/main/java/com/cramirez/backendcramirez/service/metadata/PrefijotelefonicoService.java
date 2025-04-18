package com.cramirez.backendcramirez.service.metadata;

import com.cramirez.backendcramirez.dto.metadata.PrefijotelefonicoDTO;
import com.cramirez.backendcramirez.entity.metadata.Prefijotelefonico;
import com.cramirez.backendcramirez.repository.metadata.PrefijotelefonicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrefijotelefonicoService {
    @Autowired
    private PrefijotelefonicoRepository repository;

    public List<PrefijotelefonicoDTO> getAll() {
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<PrefijotelefonicoDTO> getById(Integer id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    public PrefijotelefonicoDTO save(PrefijotelefonicoDTO dto) {
        Prefijotelefonico entity = convertToEntity(dto);
        return convertToDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private PrefijotelefonicoDTO convertToDTO(Prefijotelefonico entity) {
        PrefijotelefonicoDTO dto = new PrefijotelefonicoDTO();
        dto.setIdPrefijo(entity.getIdPrefijo());
        dto.setPrefijoPais(entity.getPrefijoPais());
        return dto;
    }

    private Prefijotelefonico convertToEntity(PrefijotelefonicoDTO dto) {
        Prefijotelefonico entity = new Prefijotelefonico();
        entity.setIdPrefijo(dto.getIdPrefijo());
        entity.setPrefijoPais(dto.getPrefijoPais());
        return entity;
    }
}
