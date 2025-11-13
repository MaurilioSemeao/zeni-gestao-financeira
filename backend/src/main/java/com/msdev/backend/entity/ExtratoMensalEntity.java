package com.msdev.backend.entity;

import com.msdev.backend.enums.StatusExtratoMensal;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusExtratoMensal status;

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

    public StatusExtratoMensal getStatus() {
        return status;
    }

    public void setStatus(StatusExtratoMensal status) {
        this.status = status;
    }

    public Set<TransacaoEntity> getTransacoes() {
        return transacoes;
    }

    public void addTransacoes(TransacaoEntity transacao) {
        this.transacoes.add(transacao);
    }

    public static  ExtratoMensalEntity criaExtratoDoMes(UsuarioEntity usuario, YearMonth mesReferencia){
        if(usuario == null){
            throw new IllegalArgumentException("Usuário não pode ser nulo para criar uma fatura");
        }

        ExtratoMensalEntity novoExtrato = new ExtratoMensalEntity();
        novoExtrato.setUsuario(usuario);
        novoExtrato.setMesReferencia(mesReferencia);

        novoExtrato.setStatus(StatusExtratoMensal.ABERTA);
        novoExtrato.setSaldoExtrato(BigDecimal.ZERO);

        return novoExtrato;
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
