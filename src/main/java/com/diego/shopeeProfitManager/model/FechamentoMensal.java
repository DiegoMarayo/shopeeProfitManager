package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "fechamento_mensal", uniqueConstraints = {@UniqueConstraint(columnNames = {"mes", "ano"}) })
public class FechamentoMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private BigDecimal faturamentoBruto;

    @Column(nullable = false)
    private Integer unidadesVendidas;

    @Column(nullable = false)
    private BigDecimal custoProdutos;

    @Column(nullable = false)
    private BigDecimal taxaMarketplacePercentual;

    @Column(nullable = false)
    private BigDecimal taxaMarketplaceFixa;

    @Column(nullable = false)
    private BigDecimal impostos;

    @Column(nullable = false)
    private BigDecimal ads;

    @Column(nullable = false)
    private BigDecimal despesasOperacionais;

    @Column(nullable = false)
    private BigDecimal lucroLiquido;

    @Column(nullable = false)
    private BigDecimal margemLiquida;

    public FechamentoMensal() {}

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

    public BigDecimal getFaturamentoBruto() {
        return faturamentoBruto;
    }

    public void setFaturamentoBruto(BigDecimal faturamentoBruto) {
        this.faturamentoBruto = faturamentoBruto;
    }

    public Integer getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Integer unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public BigDecimal getCustoProdutos() {
        return custoProdutos;
    }

    public void setCustoProdutos(BigDecimal custoProdutos) {
        this.custoProdutos = custoProdutos;
    }

    public BigDecimal getTaxaMarketplacePercentual() {
        return taxaMarketplacePercentual;
    }

    public void setTaxaMarketplacePercentual(BigDecimal taxaMarketplacePercentual) {
        this.taxaMarketplacePercentual = taxaMarketplacePercentual;
    }

    public BigDecimal getTaxaMarketplaceFixa() {
        return taxaMarketplaceFixa;
    }

    public void setTaxaMarketplaceFixa(BigDecimal taxaMarketplaceFixa) {
        this.taxaMarketplaceFixa = taxaMarketplaceFixa;
    }

    public BigDecimal getImpostos() {
        return impostos;
    }

    public void setImpostos(BigDecimal impostos) {
        this.impostos = impostos;
    }

    public BigDecimal getAds() {
        return ads;
    }

    public void setAds(BigDecimal ads) {
        this.ads = ads;
    }

    public BigDecimal getDespesasOperacionais() {
        return despesasOperacionais;
    }

    public void setDespesasOperacionais(BigDecimal despesasOperacionais) {
        this.despesasOperacionais = despesasOperacionais;
    }

    public BigDecimal getLucroLiquido() {
        return lucroLiquido;
    }

    public void setLucroLiquido(BigDecimal lucroLiquido) {
        this.lucroLiquido = lucroLiquido;
    }

    public BigDecimal getMargemLiquida() {
        return margemLiquida;
    }

    public void setMargemLiquida(BigDecimal margemLiquida) {
        this.margemLiquida = margemLiquida;
    }
}
