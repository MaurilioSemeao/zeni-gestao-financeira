package com.msdev.backend.dto.response;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ExtratoMensalResponse {

    private Long id;
    private YearMonth mesReferencia;
    private BigDecimal saldoExtrato;
    private UsuarioResponse usuario;

    public ExtratoMensalResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public YearMonth getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(YearMonth mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public BigDecimal getSaldoExtrato() {
        return saldoExtrato;
    }

    public void setSaldoExtrato(BigDecimal saldoExtrato) {
        this.saldoExtrato = saldoExtrato;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }
}
