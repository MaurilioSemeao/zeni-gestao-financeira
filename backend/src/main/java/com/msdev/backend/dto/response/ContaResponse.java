package com.msdev.backend.dto.response;

import com.fasterxml.jackson.databind.node.LongNode;

import java.math.BigDecimal;

public class ContaResponse {

    private Long id;
    private String nome;
    private BigDecimal gastos;

    public ContaResponse() {
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
}
