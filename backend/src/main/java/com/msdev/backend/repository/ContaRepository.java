package com.msdev.backend.repository;

import com.msdev.backend.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
    List<ContaEntity> findAllByUsuarioId (Long id);
}
