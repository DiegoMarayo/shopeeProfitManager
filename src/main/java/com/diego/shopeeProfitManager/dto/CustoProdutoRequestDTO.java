package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CustoProdutoRequestDTO {

    @NotNull(message = "O custo do produto é obrigatório.")
    @DecimalMin(value = "0.00", message = "O custo do produto não pode ser negativo.")
    @Digits(integer = 8, fraction = 2,
            message = "O custo do produto deve ter no máximo 8 dígitos inteiros e 2 decimais.")
    private BigDecimal custoProduto;

    @NotNull(message = "O custo da embalagem é obrigatório.")
    @DecimalMin(value = "0.00", message = "O custo da embalagem não pode ser negativo.")
    @Digits(integer = 8, fraction = 2,
            message = "O custo da embalagem deve ter no máximo 8 dígitos inteiros e 2 decimais")
    private BigDecimal custoEmbalagem;

    @NotNull(message = "As despesas extras são obrigatórias, caso não haja despesas considerar 0.00")
    @DecimalMin(value = "0.00", message = "As despesas extras não podem ser negativas.")
    @Digits(integer = 8, fraction = 2,
            message = "As despesas extras devem ter no máximo 8 dígitos inteiros e 2 decimais")
    private BigDecimal despesasExtras;

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
