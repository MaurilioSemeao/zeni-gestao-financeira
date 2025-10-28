package com.msdev.backend.dto.request;

import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.TipoTransacao;

import java.math.BigDecimal;

public class TransacaoRequest {

    private String descricao;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private MeioPagamento meioPagamento;
    private Long cartaoId;
    private boolean previsao;

    public TransacaoRequest() {
    }

    public TransacaoRequest(String descricao, BigDecimal valor, TipoTransacao tipo, MeioPagamento meioPagamento,boolean previsao) {
        this.setDescricao(descricao);
        this.setValor(valor);
        this.setTipo(tipo);
        this.setMeioPagamento(meioPagamento);
        this.setPrevisao(previsao);
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

    public Long getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(Long cartaoId) {
        this.cartaoId = cartaoId;
    }
}
