package com.msdev.backend.dto.response;

import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoResponse {

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private MeioPagamento meioPagamento;
    private boolean previsao;
    private LocalDateTime dataTransacao;

    private UsuarioResponse usuario;

    private CartaoResponse cartao;

    public TransacaoResponse() {
    }

    public TransacaoResponse(
            String descricao, BigDecimal valor,
            TipoTransacao tipo, MeioPagamento meioPagamento,
            boolean previsao, LocalDateTime dataTransacao,
            UsuarioResponse usuario, CartaoResponse cartao
    ) {
        this.setDescricao(descricao);
        this.setValor(valor);
        this.setTipo(tipo);
        this.setMeioPagamento(meioPagamento);
        this.setUsuario(usuario);
        this.setCartao(cartao);
        this.setPrevisao(previsao);
        this.setDataTransacao(dataTransacao);
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

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }

    public void setCartao(CartaoResponse cartao) {
        this.cartao = cartao;
    }
}
