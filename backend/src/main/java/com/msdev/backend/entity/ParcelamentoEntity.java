package com.msdev.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "parcelamentos")
public class ParcelamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private Integer totalParcelas;

    @Column(nullable = false)
    private LocalDate dataCompra;

    @Column(nullable = false)
    private boolean ativo;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

    @ManyToOne
    @JoinColumn(name = "cartao_id", nullable = false)
    private CartaoEntity cartao;

    @OneToMany(
            mappedBy = "parcelamento",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ParcelaEntity> parcelas;

    public ParcelamentoEntity() {
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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Integer totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public CartaoEntity getCartao() {
        return cartao;
    }

    public void setCartao(CartaoEntity cartao) {
        this.cartao = cartao;
    }

    public Set<ParcelaEntity> getParcelas() {
        return parcelas;
    }

    public void addParcela(ParcelaEntity parcela) {
       this.parcelas.add(parcela);
    }

    private void gerarParcelas(){
        BigDecimal  valorParcela = valorTotal.divide(BigDecimal.valueOf(totalParcelas));
        for ( int i = 0; i < totalParcelas; i++){
            LocalDate dataVencimento = dataCompra.plusMonths(i);
            ParcelaEntity parcela = new ParcelaEntity(valorParcela, dataVencimento, this);
            parcelas.add(parcela);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ParcelamentoEntity that = (ParcelamentoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
