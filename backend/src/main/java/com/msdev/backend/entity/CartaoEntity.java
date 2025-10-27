package com.msdev.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.msdev.backend.exception.cartao.CataoInvalidoException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;


@Entity
@Table(name = "cartoes")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String apelido;

    @Column(nullable = false)
    private String ultimosDigitos;

    @Column(nullable = false)
    private Integer quantidadeCompras;

    @Column(nullable = false)
    private BigDecimal gastos;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private UsuarioEntity usuario;

    public CartaoEntity() {
    }

    public CartaoEntity( String apelido, String ultimosDigitos, UsuarioEntity usuario) {
        this.setApelido(apelido);
        this.setUltimosDigitos(ultimosDigitos);
        this.setQuantidadeCompras(0);
        this.setGastos(BigDecimal.ZERO);
        this.setUsuario(usuario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        if(ultimosDigitos == null ){
            throw new CataoInvalidoException("Apelido não pode ser nulo.");
        }
        this.apelido = apelido;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        if(ultimosDigitos == null || ultimosDigitos.length() != 4){
            throw new CataoInvalidoException("Os últimos dígitos do cartão devem conter exatamente 4 números.");
        }
        this.ultimosDigitos = ultimosDigitos;
    }

    public Integer getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(Integer quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartaoEntity that = (CartaoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
