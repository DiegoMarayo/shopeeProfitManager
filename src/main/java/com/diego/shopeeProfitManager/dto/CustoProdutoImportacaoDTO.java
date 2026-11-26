package com.diego.shopeeProfitManager.dto;

import java.math.BigDecimal;

public class CustoProdutoImportacaoDTO {

    private Long produtoId;
    private BigDecimal custoProduto;
    private  BigDecimal custoEmbalagem;
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
