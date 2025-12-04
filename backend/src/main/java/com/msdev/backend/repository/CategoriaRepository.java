package com.msdev.backend.repository;

import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    List<CategoriaEntity> findAllByUsuarioId(Long usuarioId);

    Optional<CategoriaEntity> findByIdAndUsuarioId(Long id, Long usuarioId);
}
