package com.msdev.backend.dto.response;

import java.math.BigDecimal;

public class CarteiraResponse {
    private Long id;
    private String nome;
    private BigDecimal saldo;

    public CarteiraResponse() {
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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
