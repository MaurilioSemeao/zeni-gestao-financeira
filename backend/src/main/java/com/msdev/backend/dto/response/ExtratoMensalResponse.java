package com.msdev.backend.dto.response;

import com.msdev.backend.enums.StatusExtratoMensal;

import java.math.BigDecimal;
import java.time.YearMonth;

public class ExtratoMensalResponse {

    private Long id;
    private YearMonth mesReferencia;
    private BigDecimal saldoExtrato;
    private UsuarioResponse usuario;
    private StatusExtratoMensal status;

    public ExtratoMensalResponse() {
    }

    public ExtratoMensalResponse(YearMonth mesReferencia, BigDecimal saldoExtrato, UsuarioResponse usuario, StatusExtratoMensal status) {
        this.mesReferencia = mesReferencia;
        this.saldoExtrato = saldoExtrato;
        this.usuario = usuario;
        this.status = status;
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

    public StatusExtratoMensal getStatus() {
        return status;
    }

    public void setStatus(StatusExtratoMensal status) {
        this.status = status;
    }
}
