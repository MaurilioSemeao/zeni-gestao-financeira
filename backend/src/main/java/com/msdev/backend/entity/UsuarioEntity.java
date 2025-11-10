package com.msdev.backend.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.msdev.backend.enums.TipoUsuario;
import com.msdev.backend.exception.usuario.UsuarioInvalidoException;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @OneToMany(
            mappedBy = "usuario",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<CartaoEntity> cartoes;

    @OneToMany(
            mappedBy = "usuario",
               cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<ContaEntity> contas;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private CarteiraEntity carteira;

    public UsuarioEntity() {
       this.cartoes = new HashSet<>();
       this.contas = new HashSet<>();
       this.tipoUsuario = TipoUsuario.PADRAO;
    }

    public UsuarioEntity(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.cartoes = new HashSet<>();
        this.contas = new HashSet<>();
        this.tipoUsuario = TipoUsuario.PADRAO;
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<CartaoEntity> getCartoes() {
        return cartoes;
    }

    public void setCartao(CartaoEntity cartao) {
        this.cartoes.add(cartao);
    }


    public Set<ContaEntity> getContas() {
        return contas;
    }

    public void setConta(ContaEntity conta) {
        this.contas.add(conta);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
