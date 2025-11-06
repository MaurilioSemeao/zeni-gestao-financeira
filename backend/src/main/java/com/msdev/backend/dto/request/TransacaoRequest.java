package com.msdev.backend.dto.request;

import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.TipoTransacao;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransacaoRequest {

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "O tipo da transação é obrigatório (RECEITA ou DESPESA).")
    private TipoTransacao tipo;

    @NotNull(message = "O meio de pagamento é obrigatório (PIX, CARTÃO, CONTA, DINHEIRO, etc.).")
    private MeioPagamento meioPagamento;


    private Long cartaoId;
    private Long contaId;
    private Long carteiraId;
    private Long pixContaId;

    private boolean previsao;

    @AssertTrue(message = "O ID do cartão é obrigatório quando o meio de pagamento for CARTÃO.")
    public boolean isCartaoValido(){
        return meioPagamento !=MeioPagamento.CARTAO || cartaoId != null;
    }

    public TransacaoRequest() {
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

    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public Long getCarteiraId() {
        return carteiraId;
    }

    public void setCarteiraId(Long carteiraId) {
        this.carteiraId = carteiraId;
    }

    public Long getPixContaId() {
        return pixContaId;
    }

    public void setPixContaId(Long pixContaId) {
        this.pixContaId = pixContaId;
    }
}
