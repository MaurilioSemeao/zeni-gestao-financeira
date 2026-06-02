package com.msdev.backend.dto.response;

import java.math.BigDecimal;

public class ResumoCartaoResponse {
    private String apelidoCartao;
    private String ultimosDigitos;
    private BigDecimal valorTotal;
    private Long quantidadeCompras;
    private Double porcentagem;

    public ResumoCartaoResponse() {
    }

    public ResumoCartaoResponse(String apelidoCartao, String ultimosDigitos, BigDecimal valorTotal, Long quantidadeCompras, Double porcentagem) {
        this.apelidoCartao = apelidoCartao;
        this.ultimosDigitos = ultimosDigitos;
        this.valorTotal = valorTotal;
        this.quantidadeCompras = quantidadeCompras;
        this.porcentagem = porcentagem;
    }

    public String getApelidoCartao() {
        return apelidoCartao;
    }

    public void setApelidoCartao(String apelidoCartao) {
        this.apelidoCartao = apelidoCartao;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        this.ultimosDigitos = ultimosDigitos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getQuantidadeCompras() {
        return quantidadeCompras;
    }

    public void setQuantidadeCompras(Long quantidadeCompras) {
        this.quantidadeCompras = quantidadeCompras;
    }

    public Double getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Double porcentagem) {
        this.porcentagem = porcentagem;
    }
}
