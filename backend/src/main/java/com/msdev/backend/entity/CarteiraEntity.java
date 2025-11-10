package com.msdev.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "carteiras")
public class CarteiraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private BigDecimal saldo;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UsuarioEntity usuario;

    @OneToMany(
            mappedBy = "carteira",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TransacaoEntity> transacoes;

    public CarteiraEntity() {
        this.saldo = BigDecimal.ZERO;
        this.transacoes = new HashSet<>();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Set<TransacaoEntity> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(Set<TransacaoEntity> transacoes) {
        this.transacoes = transacoes;
    }

    public void addTransacao(TransacaoEntity transacao){
        this.transacoes.add(transacao);
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraEntity that = (CarteiraEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
