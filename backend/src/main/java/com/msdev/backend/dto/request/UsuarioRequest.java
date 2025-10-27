package com.msdev.backend.dto.request;

import com.msdev.backend.enums.TipoUsuario;
import com.msdev.backend.exception.usuario.UsuarioInvalidoException;

public class UsuarioRequest {

    private String nome;
    private String email;
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
        if(nome == null){
            throw new UsuarioInvalidoException("O nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null){
            throw new UsuarioInvalidoException("O email não pode ser nulo.");
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null){
            throw new UsuarioInvalidoException("A senha não pode ser nulo.");
        }
        if(senha.length() < 8 ) {
            throw new UsuarioInvalidoException("A senha deve conter 8 ou mais dígitos.");
        }
        this.senha = senha;
    }

}
