package com.diego.shopeeProfitManager.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto_variacao")
public class ProdutoVariacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "id_variacao_marketplace", unique = true)
    private Long idVariacaoMarketplace;

    @Column(name = "nome_variacao")
    private String nomeVariacao;

    @Column(name = "vendas", precision = 15, scale = 2)
    private BigDecimal vendas;

    @Column(name= "qtd_vendidos")
    private Integer qtdVendidos;

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

    public BigDecimal getVendas() {
        return vendas;
    }

    public void setVendas(BigDecimal vendas) {
        this.vendas = vendas;
    }

    public Integer getQtdVendidos() {
        return qtdVendidos;
    }

    public void setQtdVendidos(Integer qtdVendidos) {
        this.qtdVendidos = qtdVendidos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
