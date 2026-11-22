package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "desempenho_mensal", uniqueConstraints = {@UniqueConstraint(columnNames = {"produto_id",
        "mes_referencia", "ano_referencia"})})
public class DesempenhoMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mes_referencia", nullable = false)
    private Integer mesReferencia;

    @Column(name = "ano_referencia", nullable = false)
    private Integer anoReferencia;

    @Column(name = "vendas_total", precision = 15, scale = 2)
    private BigDecimal vendasTotal;

    @Column(name = "qtd_vendidos")
    private Integer qtdVendidos;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public Long getId() {
        return id;
    }

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Integer getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(Integer anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public BigDecimal getVendasTotal() {
        return vendasTotal;
    }

    public void setVendasTotal(BigDecimal vendasTotal) {
        this.vendasTotal = vendasTotal;
    }

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(Integer qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

