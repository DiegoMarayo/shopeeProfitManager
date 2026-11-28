package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {

    @Positive(message = "O ID do produto no marketplace deve ser positivo.")
    private Long idProdutoMarketplace;

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(max = 500, message = "O nome do produto deve ter no máximo 500 caracteres.")
    private String nomeProduto;

    public Long getIdProdutoMarketplace() {
        return idProdutoMarketplace;
    }

    public void setIdProdutoMarketplace(Long idProdutoMarketplace) {
        this.idProdutoMarketplace = idProdutoMarketplace;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
