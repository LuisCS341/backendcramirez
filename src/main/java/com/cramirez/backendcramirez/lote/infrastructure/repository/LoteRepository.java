package com.cramirez.backendcramirez.lote.infrastructure.repository;
import com.cramirez.backendcramirez.lote.domain.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
    int countByClienteIdClienteClone(int idClienteClone);

    List<Lote> findByClienteIdClienteClone(int idClienteClone);


    Optional<Lote> findTopByOrderByIdLoteDesc();

}
