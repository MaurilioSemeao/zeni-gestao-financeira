package com.msdev.backend.repository;

import com.msdev.backend.entity.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<ContaEntity, Long> {
}
