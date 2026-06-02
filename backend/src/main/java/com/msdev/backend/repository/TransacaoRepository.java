package com.msdev.backend.repository;

import com.msdev.backend.dto.response.ResumoCartaoResponse;
import com.msdev.backend.dto.response.ResumoCategoriaResponse;
import com.msdev.backend.entity.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
    List<TransacaoEntity> findAllByUsuarioId (Long id);

    List<TransacaoEntity> findByExtrato_Usuario_IdAndExtrato_Id(Long usuarioId, Long extratoMensalId);

    @Query("SELECT new com.msdev.backend.dto.response.ResumoCategoriaResponse(" +
            "   t.categoria.nome, SUM(t.valor), CAST(0.0 as double)) " + // 0.0 é placeholder da porcentagem
            "FROM TransacaoEntity t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.tipo = 'DESPESA' " +
            "AND t.dataTransacao BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY t.categoria.nome")
    List<ResumoCategoriaResponse> findGastosPorCategoria(
            @Param("usuarioId") Long usuarioId,
            @Param("dataInicio") LocalDateTime daInicio,
            @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT new com.msdev.backend.dto.response.ResumoCartaoResponse(" +
            "   t.cartao.apelido, t.cartao.ultimosDigitos, SUM(t.valor), COUNT(t), CAST(0.0 as double)) " +
            "FROM TransacaoEntity t " +
            "WHERE t.usuario.id = :usuarioId " +
            "AND t.tipo = 'DESPESA' " +
            "AND t.cartao IS NOT NULL " +
            "AND t.dataTransacao BETWEEN :dataInicio AND :dataFim " +
            "GROUP BY t.cartao.apelido, t.cartao.ultimosDigitos")
    List<ResumoCartaoResponse> findGastosPorCartao(
            @Param("usuarioId") Long usuarioId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);
}
