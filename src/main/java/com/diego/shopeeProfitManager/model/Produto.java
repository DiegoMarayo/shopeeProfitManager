package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;


@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto_marketplace", unique = true)
    private Long idProdutoMarketplace;

    @Column(name = "nome_produto", nullable = false, length = 500)
    private String nomeProduto;

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

}
