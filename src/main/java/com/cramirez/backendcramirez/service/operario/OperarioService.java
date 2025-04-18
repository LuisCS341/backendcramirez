package com.cramirez.backendcramirez.service.operario;

import com.cramirez.backendcramirez.dto.operario.OperarioDTO;
import com.cramirez.backendcramirez.entity.operario.Operario;
import com.cramirez.backendcramirez.repository.operario.OperarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperarioService {
    private final OperarioRepository operarioRepository;

    public OperarioService(OperarioRepository operarioRepository) {
        this.operarioRepository = operarioRepository;
    }

    public List<OperarioDTO> findAll() {
        return operarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OperarioDTO findById(int id) {
        Operario operario = operarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operario no encontrado con ID: " + id));
        return convertToDTO(operario);
    }

    public OperarioDTO save(OperarioDTO operarioDTO) {
        Operario operario = convertToEntity(operarioDTO);
        Operario savedOperario = operarioRepository.save(operario);
        return convertToDTO(savedOperario);
    }

    public void deleteById(int id) {
        operarioRepository.deleteById(id);
    }

    private OperarioDTO convertToDTO(Operario operario) {
        return new OperarioDTO(
                operario.getIdOperario(),
                operario.getTipoIdentidad(),
                operario.getTipoOperario()
        );
    }

    private Operario convertToEntity(OperarioDTO dto) {
        return new Operario(
                dto.getIdOperario(),
                dto.getTipoIdentidad(),
                dto.getTipoOperario()
        );
    }
}
