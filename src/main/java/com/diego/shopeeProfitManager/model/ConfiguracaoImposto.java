package com.diego.shopeeProfitManager.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name= "configuracao_imposto", uniqueConstraints = { @UniqueConstraint(columnNames = {"mes", "ano"})})
public class ConfiguracaoImposto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal impostoPercentual;

    public ConfiguracaoImposto() {}

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

    public BigDecimal getImpostoPercentual() {
        return impostoPercentual;
    }

    public void setImpostoPercentual(BigDecimal impostoPercentual) {
        this.impostoPercentual = impostoPercentual;
    }
}
