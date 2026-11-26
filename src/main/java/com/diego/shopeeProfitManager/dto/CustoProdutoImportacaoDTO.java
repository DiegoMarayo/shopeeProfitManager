package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CustoProdutoImportacaoDTO {

    @NotNull(message = "O ID do produto é obrigatório.")
    @Positive(message = "O ID do produto deve ser positivo.")
    private Long produtoId;

    @NotNull(message = "O custo do produto é obrigatório.")
    @DecimalMin(value = "0.00", message = "O custo do produto deve ser maior ou igual a zero.")
    private BigDecimal custoProduto;

    @NotNull(message = "O custo da embalagem é obrigatório.")
    @DecimalMin(value = "0.00", message = "O custo da embalagem deve ser maior ou igual a zero.")
    private  BigDecimal custoEmbalagem;

    @NotNull(message = "As despesas extras são obrigatórias.")
    @DecimalMin(value = "0.00", message = "As despesas extras devem ser maior ou igual a zero.")
    private BigDecimal despesasExtras;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

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
