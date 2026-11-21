package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto_marketplace", unique = true)
    private Long idProdutoMarketplace;

    @Column(name = "nome_produto", nullable = false, length = 500)
    private String nomeProduto;

    @Column(name = "vendas_total", precision = 15, scale = 2)
    private BigDecimal vendasTotal;

    @Column(name = "qtd_vendidos")
    private Integer qtdVendidos;

    public Produto() {}

    public Long getId() {
        return id;
    }

    public Long getIdProdutoMarketplace() {
        return idProdutoMarketplace;
    }

    public void setIdProdutoMarketplace(Long idProdutoMarketplace) {
        this.idProdutoMarketplace = idProdutoMarketplace;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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
}
