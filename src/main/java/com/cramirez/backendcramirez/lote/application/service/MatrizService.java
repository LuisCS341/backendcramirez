    package com.cramirez.backendcramirez.lote.application.service;

    import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DepartamentoRepository;
    import com.cramirez.backendcramirez.localizacion.infrastructure.repository.DistritoRepository;
    import com.cramirez.backendcramirez.localizacion.infrastructure.repository.ProvinciaRepository;
    import com.cramirez.backendcramirez.localizacion.infrastructure.repository.UbicacionRepository;
    import com.cramirez.backendcramirez.lote.domain.entity.Matriz;
    import com.cramirez.backendcramirez.lote.dto.MatrizDTO;
    import com.cramirez.backendcramirez.lote.infrastructure.repository.MatrizRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.Optional;

    @Service
    public class MatrizService {

        private final MatrizRepository matrizRepository;
        private final DistritoRepository distritoRepository;
        private final ProvinciaRepository provinciaRepository;
        private final DepartamentoRepository departamentoRepository;
        private final UbicacionRepository ubicacionRepository;

        @Autowired
        public MatrizService(MatrizRepository matrizRepository, DistritoRepository distritoRepository, ProvinciaRepository provinciaRepository, DepartamentoRepository departamentoRepository, UbicacionRepository ubicacionRepository) {
            this.matrizRepository = matrizRepository;
            this.distritoRepository = distritoRepository;
            this.provinciaRepository = provinciaRepository;
            this.departamentoRepository = departamentoRepository;
            this.ubicacionRepository = ubicacionRepository;
        }

        public Optional<MatrizDTO> getMatrizById(Integer id) {
            return matrizRepository.findById(id).map(this::convertToMatrizDTO);
        }

        public Optional<MatrizDTO> getMatrizByIdLote(Integer idLote) {
            return matrizRepository.findByIdLote(idLote).map(this::convertToMatrizDTO);
        }

        public MatrizDTO saveMatriz(MatrizDTO matrizDTO) {
            Matriz matriz = convertToMatrizEntity(matrizDTO);
            Matriz savedMatriz = matrizRepository.save(matriz);
            return convertToMatrizDTO(savedMatriz);
        }

        public void deleteMatriz(Integer id) {
            matrizRepository.deleteById(id);
        }

        private MatrizDTO convertToMatrizDTO(Matriz matriz) {
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

            dto.setDistritoMatriz(obtenerTexto(distritoRepository.findById(matriz.getIdDistritoMatriz()), "DistritoMatriz"));
            dto.setDepartamentoMatriz(obtenerTexto(departamentoRepository.findById(matriz.getIdProvinciaMatriz()), "ProvinciaMatriz"));
            dto.setProvinciaMatriz(obtenerTexto(provinciaRepository.findById(matriz.getIdDepartamentoMatriz()), "DepartamentoMatriz"));
            dto.setUbicacionMatriz(obtenerTexto(ubicacionRepository.findById(matriz.getIdUbicacion()), "UbicacionMatriz"));

            return dto;
        }

        private Matriz convertToMatrizEntity(MatrizDTO dto) {
            Matriz matriz = new Matriz();
            matriz.setIdMatriz(dto.getIdMatriz());
            matriz.setIdLote(dto.getIdLote());
            matriz.setIdDepartamentoMatriz(dto.getIdDepartamentoMatriz());
            matriz.setIdProvinciaMatriz(dto.getIdProvinciaMatriz());
            matriz.setIdDistritoMatriz(dto.getIdDistritoMatriz());
            matriz.setIdUbicacion(dto.getIdUbicacion());
            matriz.setAreaMatrizHasMatriz(dto.getAreaMatrizHasMatriz());
            matriz.setRegistroMatriz(dto.getRegistroMatriz());
            matriz.setPartidaMatriz(dto.getPartidaMatriz());
            matriz.setUnidadCatastralMatriz(dto.getUnidadCatastralMatriz());
            matriz.setUrbanizacionMatriz(dto.getUrbanizacionMatriz());
            matriz.setCompraventaMatriz(dto.getCompraventaMatriz());
            matriz.setSituacionLegalMatriz(dto.getSituacionLegalMatriz());
            return matriz;
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
    }

