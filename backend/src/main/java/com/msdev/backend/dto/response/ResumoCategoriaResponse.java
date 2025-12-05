package com.msdev.backend.dto.response;

import java.math.BigDecimal;

public class ResumoCategoriaResponse {
    private String nomeCategoria;
    private BigDecimal valorTotal;
    private Double porcentagem;

    public ResumoCategoriaResponse() {
    }

    public ResumoCategoriaResponse(String nomeCategoria, BigDecimal valorTotal, Double porcentagem) {
        this.nomeCategoria = nomeCategoria;
        this.valorTotal = valorTotal;
        this.porcentagem = porcentagem;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }
}
