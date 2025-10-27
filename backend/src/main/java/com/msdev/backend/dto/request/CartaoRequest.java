package com.msdev.backend.dto.request;


public class CartaoRequest {

    private String apelido;
    private Integer ultimosDigitos;

    public CartaoRequest() {
    }

    public CartaoRequest(String apelido, Integer ultimosDigitos) {
        this.setApelido(apelido);
        this.setUltimosDigitos(ultimosDigitos);
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
}
