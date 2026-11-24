package com.diego.shopeeProfitManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

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
