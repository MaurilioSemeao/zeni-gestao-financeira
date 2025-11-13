package com.msdev.backend.repository;

import com.msdev.backend.entity.ExtratoMensalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.YearMonth;
import java.util.Optional;

public interface ExtratoMensalRepository extends JpaRepository<ExtratoMensalEntity, Long> {

    Optional<ExtratoMensalEntity> findByUsuarioIdAndMesReferencia(Long usuarioId, YearMonth mesReferencia);
}
