package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class DespesaAdsMensalRequestDTO {

    @NotNull(message = "A despesa mensal de ADS é obrigatória, caso não haja informe R$ 0.00")
    @DecimalMin(value = "0.00", message = "A despesa mensal de ADS não pode ser negativa.")
    @Digits(integer = 8, fraction = 2,
            message = "A despesa mensal de ADS deve ter no máximo 8 dígitos inteiros e 2 casas decimais.")
    private BigDecimal adsMensal;

    public BigDecimal getAdsMensal() {
        return adsMensal;
    }

    public void setAdsMensal(BigDecimal adsMensal) {
        this.adsMensal = adsMensal;
    }
}
