package com.diego.shopeeProfitManager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class DespesaOperacionalRequestDTO {

    @NotBlank(message = "A descrição da despesa é obrigatória.")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @NotBlank(message = "A categoria da despesa é obrigatória.")
    @Size(max = 100, message = "A categoria deve ter no máximo 100 caracteres.")
    private String categoria;

    @NotNull(message = "O valor da despesa é obrigatório.")
    @DecimalMin(value = "0.00", message = "O valor da despesa não pode ser negativo.")
    @Digits(integer = 8, fraction = 2,
            message = "O valor deve ter no máximo 8 dígitos inteiros e 2 casas decimais.")
    private BigDecimal valor;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}