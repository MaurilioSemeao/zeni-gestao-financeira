package com.msdev.backend.dto.request;


import com.msdev.backend.exception.cartao.CataoInvalidoException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CartaoRequest {

    @NotBlank(message = "O apelido é obrigatório")
    private String apelido;

    @NotBlank(message = "Os últimos 4 dígitos são obrigatórios.")
    @Size(min = 4, max = 4, message = "O campo deve conter exatamente 4 dígitos.")
    @Pattern(regexp = "\\d{4}", message = "O campo deve conter apenas dígitos numéricos.")
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
        this.apelido = apelido;
    }

    public String getUltimosDigitos() {
        return ultimosDigitos;
    }

    public void setUltimosDigitos(String ultimosDigitos) {
        this.ultimosDigitos = ultimosDigitos;
    }
}
