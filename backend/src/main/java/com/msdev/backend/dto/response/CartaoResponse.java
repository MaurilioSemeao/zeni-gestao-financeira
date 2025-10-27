package com.msdev.backend.dto.response;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class CartaoResponse {

    private Long id;
    private String apelido;
    private Integer ultimosDigitos;
    private Integer quantidadeCompras;
    private BigDecimal gastos;

    public CartaoResponse() {
    }

    public CartaoResponse(Long id, String apelido, Integer ultimosDigitos, Integer quantidadeCompras, BigDecimal gastos) {
        this.setId(id);
        this.setApelido(apelido);
        this.setUltimosDigitos(ultimosDigitos);
        this.setQuantidadeCompras(quantidadeCompras);
        this.setGastos(gastos);
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
        this.apelido = apelido;
    }

    public Integer getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(Integer ultimosDigitos) {
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
}
