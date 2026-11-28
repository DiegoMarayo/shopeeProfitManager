package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ConfiguracaoMarketplaceRequestDTO {

    @NotNull(message = " A taxa percentual é obrigatória.")
    @DecimalMin(value = "0.00", message = "A taxa percentual não pode ser negativa.")
    @DecimalMax(value = "30.00", message = "A taxa percentual não pode ser maior que 30%")
    @Digits(integer = 8, fraction = 2,
            message = "A taxa percentual pode ter no máximo 8 dígitos inteiros e 2 casas decimais.")
    private BigDecimal taxaPercentual;

    @NotNull(message = "A taxa fixa por pedido é obrigatória.")
    @DecimalMin(value = "0.00", message = "A taxa fixa por pedido não pode ser negativa.")
    @DecimalMax(value = "100.00", message = "A taxa fixa por pedido não pode ser maior que R$ 100.00")
    @Digits(integer = 8, fraction = 2,
            message = "A taxa por pedido deve ter no máximo 8 dígitos inteiros e 2 casas decimais.")
    private BigDecimal taxaFixaPorPedido;

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
