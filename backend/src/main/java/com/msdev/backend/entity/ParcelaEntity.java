package com.msdev.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcela")
public class ParcelaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private BigDecimal valorParcela;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Column(nullable = false)
    private boolean gerada;

    @ManyToOne
    @JoinColumn( name = "parcelamento_id", nullable = false)
    private ParcelamentoEntity parcelamento;

    public ParcelaEntity() {
    }

    public ParcelaEntity(BigDecimal valorParcela, LocalDate dataVencimento, ParcelamentoEntity parcelamento) {
        this.valorParcela = valorParcela;
        this.dataVencimento = dataVencimento;
        this.parcelamento = parcelamento;
        this.gerada = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isGerada() {
        return gerada;
    }

    public void setGerada(boolean gerada) {
        this.gerada = gerada;
    }

    public ParcelamentoEntity getParcelamento() {
        return parcelamento;
    }

    public void setParcelamento(ParcelamentoEntity parcelamento) {
        this.parcelamento = parcelamento;
    }
}
