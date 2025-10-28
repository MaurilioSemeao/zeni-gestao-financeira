package com.msdev.backend.dto.request;

import com.msdev.backend.enums.TipoUsuario;
import com.msdev.backend.exception.usuario.UsuarioInvalidoException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8)
    private String senha;

    public UsuarioRequest() {
    }

    public UsuarioRequest( String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
