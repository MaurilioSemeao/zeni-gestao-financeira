package com.msdev.backend.repository;

import com.msdev.backend.entity.CarteiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteiraRepository extends JpaRepository<CarteiraEntity, Long> {
    List<CarteiraEntity> findAllByUsuarioId (Long id);
}
