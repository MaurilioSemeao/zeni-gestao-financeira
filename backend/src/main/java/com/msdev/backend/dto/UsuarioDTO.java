package com.msdev.backend.dto;

import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.enums.TipoUsuario;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(UsuarioEntity usuario) {
        BeanUtils.copyProperties(usuario, this, "id");
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
