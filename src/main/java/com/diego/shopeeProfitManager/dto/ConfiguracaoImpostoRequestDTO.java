package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ConfiguracaoImpostoRequestDTO {

    @NotNull(message = "O percentual de imposto é obrigatório.")
    @DecimalMin(value = "0.000", message = "O percentual de imposto não pode ser negativo.")
    @DecimalMax(value = "20.000", message = "O percentual de imposto não pode ser maior que 20%.")
    @Digits(integer = 7, fraction = 3,
            message = "O percentual de imposto deve ter no máximo 7 dígitos inteiros e 3 casas decimais.")
    private BigDecimal impostoPercentual;

    public BigDecimal getImpostoPercentual() {
        return impostoPercentual;
    }

    public void setImpostoPercentual(BigDecimal impostoPercentual) {
        this.impostoPercentual = impostoPercentual;
    }
}