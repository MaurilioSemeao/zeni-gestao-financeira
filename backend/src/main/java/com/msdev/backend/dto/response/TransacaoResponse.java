package com.msdev.backend.dto.response;

import com.msdev.backend.dto.request.CategoriaRequest;
import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransacaoResponse {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoTransacao tipo;
    private MeioPagamento meioPagamento;
    private boolean previsao;
    private String dataTransacao;


    private UsuarioResponse usuario;
    private CategoriaResponse categoria;
    private CartaoResponse cartao;

    public TransacaoResponse() {
    }

    public TransacaoResponse(
            String descricao, BigDecimal valor,
            TipoTransacao tipo, MeioPagamento meioPagamento,
            boolean previsao, LocalDateTime dataTransacao,
            UsuarioResponse usuario, CartaoResponse cartao, CategoriaResponse categoria
    ) {
        this.setDescricao(descricao);
        this.setValor(valor);
        this.setTipo(tipo);
        this.setMeioPagamento(meioPagamento);
        this.setUsuario(usuario);
        this.setCartao(cartao);
        this.setCategoria(categoria);
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

    public String getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao.format(dtf);
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

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponse categoria) {
        this.categoria = categoria;
    }
}
