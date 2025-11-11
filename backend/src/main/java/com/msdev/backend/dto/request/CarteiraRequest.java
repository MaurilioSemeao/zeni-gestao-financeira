package com.msdev.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CarteiraRequest {

    @NotBlank(message = "O nome Da carteira não pode ser nulo")
    private String nome;

    public CarteiraRequest() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
