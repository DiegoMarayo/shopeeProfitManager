package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name =  "configuracao_marketplace", uniqueConstraints = { @UniqueConstraint(columnNames = {"mes", "ano"})})
public class ConfiguracaoMarketplace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal taxaPercentual;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal taxaFixaPorPedido;

    public ConfiguracaoMarketplace() {}

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

    public BigDecimal getTaxaPercentual() {
        return taxaPercentual;
    }

    public void setTaxaPercentual(BigDecimal taxaPercentual) {
        this.taxaPercentual = taxaPercentual;
    }

    public BigDecimal getTaxaFixaPorPedido() {
        return taxaFixaPorPedido;
    }

    public void setTaxaFixaPorPedido(BigDecimal taxaFixaPorPedido) {
        this.taxaFixaPorPedido = taxaFixaPorPedido;
    }
}
