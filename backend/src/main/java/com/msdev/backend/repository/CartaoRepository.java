package com.msdev.backend.repository;

import com.msdev.backend.entity.CartaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {
    List<CartaoEntity> findAllByUsuarioId(Long id);
}
