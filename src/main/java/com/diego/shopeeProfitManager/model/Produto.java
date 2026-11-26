package com.diego.shopeeProfitManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto_marketplace", unique = true)
    @Positive(message = "O ID do produto no marketplace deve ser positivo.")
    private Long idProdutoMarketplace;

    @NotBlank(message = "O nome do produto é obrigatório.")
    @Size(max = 500, message = "O nome do produto deve ter no máximo 500 caracteres.")
    @Column(name = "nome_produto", nullable = false, length = 500)
    private String nomeProduto;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<CustoProduto> custosProduto;

    public Produto() {}

    public Long getId() {
        return id;
    }

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

    public List<CustoProduto> getCustosProduto() {
        return custosProduto;
    }

    public void setCustosProduto(List<CustoProduto> custosProduto) {
        this.custosProduto = custosProduto;
    }
}
