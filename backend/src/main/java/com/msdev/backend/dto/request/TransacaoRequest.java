package com.msdev.backend.dto.request;

import com.msdev.backend.enums.MeioPagamento;
import com.msdev.backend.enums.TipoTransacao;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TransacaoRequest {

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser maior que zero")
    private BigDecimal valor;

    @NotNull(message = "O tipo da transação é obrigatório (RECEITA ou DESPESA).")
    private TipoTransacao tipo;

    @NotNull(message = "O meio de pagamento é obrigatório (PIX, CARTÃO, CONTA, DINHEIRO, etc.).")
    private MeioPagamento meioPagamento;


    private Long categoriaId;

    private Long cartaoId;
    private Long contaId;
    private Long carteiraId;


    private boolean previsao;

    @AssertTrue(message = "O ID do cartão é obrigatório quando o meio de pagamento for CREDITO.")
    public boolean isCartaoValido(){
        return meioPagamento !=MeioPagamento.CREDITO || cartaoId != null;
    }

    @AssertTrue(message = "O ID Da conta é obrigatório quando o meio de pagamento dor DEBITO ou PIX")
    public boolean isContaValido(){
        if(meioPagamento == MeioPagamento.DEBITO || meioPagamento == MeioPagamento.PIX){
            return contaId != null;
        }
        return true;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
