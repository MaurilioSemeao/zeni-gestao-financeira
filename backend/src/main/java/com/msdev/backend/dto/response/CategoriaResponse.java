package com.msdev.backend.dto.response;

import java.util.Set;

public class CategoriaResponse {

    private Long id;
    private String nome;
    private boolean padrao;

    public CategoriaResponse() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPadrao() {
        return padrao;
    }

    public void setPadrao(boolean padrao) {
        this.padrao = padrao;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
