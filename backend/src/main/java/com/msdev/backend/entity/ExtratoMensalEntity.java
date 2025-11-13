package com.msdev.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "extratos")
public class ExtratoMensalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private YearMonth mesReferencia;

    private BigDecimal saldoExtrato;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(
            mappedBy = "extrato",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TransacaoEntity> transacoes;

    public ExtratoMensalEntity() {
        this.mesReferencia = YearMonth.now();
        this.saldoExtrato = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YearMonth getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(YearMonth mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public BigDecimal getSaldoExtrato() {
        return saldoExtrato;
    }

    public void setSaldoExtrato(BigDecimal saldoExtrato) {
        this.saldoExtrato = saldoExtrato;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Set<TransacaoEntity> getTransacoes() {
        return transacoes;
    }

    public void addTransacoes(TransacaoEntity transacao) {
        this.transacoes.add(transacao);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ExtratoMensalEntity that = (ExtratoMensalEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
