package com.msdev.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "contas")
public class ContaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private BigDecimal gastos;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private UsuarioEntity usuario;

    @OneToMany(
            mappedBy = "conta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TransacaoEntity> transacoes;

    public ContaEntity() {
        this.transacoes = new HashSet<>();
        this.gastos = BigDecimal.ZERO;
    }

    public ContaEntity(String nome, BigDecimal gastos, UsuarioEntity usuario) {
        this.nome = nome;
        this.gastos = gastos;
        this.usuario = usuario;
        this.transacoes = new HashSet<>();
        this.gastos = BigDecimal.ZERO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getGastos() {
        return gastos;
    }

    public void setGastos(BigDecimal gastos) {
        this.gastos = gastos;
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

    public void somaValorTotal(BigDecimal valor){
        this.gastos = this.gastos.add(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContaEntity that = (ContaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
