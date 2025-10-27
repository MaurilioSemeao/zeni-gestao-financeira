package com.msdev.backend.dto.request;


import com.msdev.backend.exception.cartao.CataoInvalidoException;

public class CartaoRequest {

    private String apelido;
    private String ultimosDigitos;

    public CartaoRequest() {
    }

    public CartaoRequest(String apelido, String ultimosDigitos) {
        this.setApelido(apelido);
        this.setUltimosDigitos(ultimosDigitos);
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        if(apelido == null){
            throw new CataoInvalidoException("Apelido não pode ser nulo.");
        }
        this.apelido = apelido;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        if(ultimosDigitos == null || ultimosDigitos.length() != 4){
            throw new CataoInvalidoException("Os últimos dígitos do cartão devem conter exatamente 4 números. ");
        }
        this.ultimosDigitos = ultimosDigitos;
    }
}
