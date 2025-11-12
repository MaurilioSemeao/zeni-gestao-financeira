package com.msdev.backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean padrao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @OneToMany(
            mappedBy = "categoria",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TransacaoEntity> transacoes;

    @OneToMany(
            mappedBy = "categoria",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ParcelamentoEntity> parcelamentos;

    public CategoriaEntity() {
        this.transacoes = new HashSet<>();
    }

    public CategoriaEntity(String nome, boolean padrao, UsuarioEntity usuario) {
        this.nome = nome;
        this.padrao = padrao;
        this.usuario = usuario;
        this.transacoes = new HashSet<>();
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

    public Set<TransacaoEntity> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(Set<TransacaoEntity> transacoes) {
        this.transacoes = transacoes;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Set<ParcelamentoEntity> getParcelamentos() {
        return parcelamentos;
    }

    public void addParcelamento(ParcelamentoEntity parcelamento) {
        this.parcelamentos.add(parcelamento);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaEntity that = (CategoriaEntity) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }
}


