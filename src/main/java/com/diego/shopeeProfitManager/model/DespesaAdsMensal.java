package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "despesa_ads_mensal", uniqueConstraints = {@UniqueConstraint(columnNames = {"mes", "ano"})})
public class DespesaAdsMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal adsMensal;

    public DespesaAdsMensal() {}

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

    public BigDecimal getAdsMensal() {
        return adsMensal;
    }

    public void setAdsMensal(BigDecimal adsMensal) {
        this.adsMensal = adsMensal;
    }
}
