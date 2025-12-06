package com.msdev.backend.repository;

import com.msdev.backend.entity.ExtratoMensalEntity;
import com.msdev.backend.enums.StatusExtratoMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface ExtratoMensalRepository extends JpaRepository<ExtratoMensalEntity, Long> {

    List<ExtratoMensalEntity> findAllByUsuarioId (Long id);

    Optional<ExtratoMensalEntity> findByUsuarioIdAndMesReferencia(Long usuarioId, YearMonth mesReferencia);

    List<ExtratoMensalEntity> findAllByMesReferenciaAndStatus(YearMonth mesReferencia, StatusExtratoMensal status);
}
