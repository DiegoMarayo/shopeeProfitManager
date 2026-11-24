package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "custo_produto", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_produto", "mes", "ano"})})
public class CustoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal custoProduto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal custoEmbalagem;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal despesasExtras;

    public CustoProduto() {}

    public Long getId() {
        return id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getCustoProduto() {
        return custoProduto;
    }

    public void setCustoProduto(BigDecimal custoProduto) {
        this.custoProduto = custoProduto;
    }

    public BigDecimal getCustoEmbalagem() {
        return custoEmbalagem;
    }

    public void setCustoEmbalagem(BigDecimal custoEmbalagem) {
        this.custoEmbalagem = custoEmbalagem;
    }

    public BigDecimal getDespesasExtras() {
        return despesasExtras;
    }

    public void setDespesasExtras(BigDecimal despesasExtras) {
        this.despesasExtras = despesasExtras;
    }
}
