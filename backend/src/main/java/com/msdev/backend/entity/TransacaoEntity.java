package com.msdev.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.TipoTransacao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Transacoes")
public class TransacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MeioPagamento meioPagamento;

    @Column(nullable = false)
    private boolean previsao;

    @Column(nullable = false)
    private LocalDateTime dataTransacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = true)
    @JsonBackReference
    private CartaoEntity cartao;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = true)
    @JsonBackReference
    private ContaEntity conta;

    @ManyToOne
    @JoinColumn(name = "carteria_id", nullable = true)
    @JsonBackReference
    private CarteiraEntity carteira;


    public TransacaoEntity() {
        this.valor = BigDecimal.ZERO;
    }

    public TransacaoEntity(
            String descricao,
            TipoTransacao tipo, MeioPagamento meioPagamento,
            boolean previsao, LocalDateTime dataTransacao,
            UsuarioEntity usuario
    ) {
        this.setDescricao(descricao);
        this.setTipo(tipo);
        this.setMeioPagamento(meioPagamento);
        this.setUsuario(usuario);
        this.setPrevisao(previsao);
        this.setDataTransacao(dataTransacao);
        this.valor = BigDecimal.ZERO;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public MeioPagamento getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(MeioPagamento meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public boolean isPrevisao() {
        return previsao;
    }

    public void setPrevisao(boolean previsao) {
        this.previsao = previsao;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public CartaoEntity getCartao() {
        return cartao;
    }

    public void setCartao(CartaoEntity cartao) {
        this.cartao = cartao;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
    }

    public CarteiraEntity getCarteira() {
        return carteira;
    }

    public void setCarteira(CarteiraEntity carteira) {
        this.carteira = carteira;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TransacaoEntity that = (TransacaoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
