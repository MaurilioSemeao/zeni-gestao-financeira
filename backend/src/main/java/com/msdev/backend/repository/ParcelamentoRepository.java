package com.msdev.backend.repository;

import com.msdev.backend.entity.ParcelamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelamentoRepository extends JpaRepository<ParcelamentoEntity, Long> {
    List<ParcelamentoEntity> findAllByUsuarioId (Long id);
}
