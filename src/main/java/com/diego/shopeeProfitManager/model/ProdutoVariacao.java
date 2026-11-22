package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;


@Entity
@Table(name = "produto_variacao")
public class ProdutoVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "id_variacao_marketplace", unique = true, nullable = false)
    private Long idVariacaoMarketplace;

    @Column(name = "nome_variacao")
    private String nomeVariacao;

    @ManyToOne
    @JoinColumn(name= "id_produto", nullable = false)
    private Produto produto;

    public ProdutoVariacao() {}

    public Long getId() {
        return id;
    }

    public Long getIdVariacaoMarketplace() {
        return idVariacaoMarketplace;
    }

    public void setIdVariacaoMarketplace(Long idVariacaoMarketplace) {
        this.idVariacaoMarketplace = idVariacaoMarketplace;
    }

    public String getNomeVariacao() {
        return nomeVariacao;
    }

    public void setNomeVariacao(String nomeVariacao) {
        this.nomeVariacao = nomeVariacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
