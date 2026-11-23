package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "desempenho_variacao_mensal", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"id_produto_variacao", "mes", "ano"})})

public class DesempenhoVariacaoMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer mes;

    private Integer ano;

    private BigDecimal vendas;

    private Integer qtdVendidos;

    @ManyToOne
    @JoinColumn(name = "id_produto_variacao", nullable = false)
    private ProdutoVariacao produtoVariacao;

    public DesempenhoVariacaoMensal() {}

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

    public BigDecimal getVendas() {
        return vendas;
    }

    public void setVendas(BigDecimal vendas) {
        this.vendas = vendas;
    }

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(Integer qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public ProdutoVariacao getProdutoVariacao() {
        return produtoVariacao;
    }

    public void setProdutoVariacao(ProdutoVariacao produtoVariacao) {
        this.produtoVariacao = produtoVariacao;
    }
}
