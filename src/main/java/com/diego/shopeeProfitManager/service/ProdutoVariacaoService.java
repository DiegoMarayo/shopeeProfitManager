package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.model.ProdutoVariacao;
import com.diego.shopeeProfitManager.repository.ProdutoVariacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoVariacaoService {

    private final ProdutoVariacaoRepository produtoVariacaoRepository;

    public ProdutoVariacaoService(ProdutoVariacaoRepository produtoVariacaoRepository) {
        this.produtoVariacaoRepository = produtoVariacaoRepository;
    }

    public Optional<ProdutoVariacao> buscarPorIdMarketplace(Long idVariacaoMarketplace) {
        return produtoVariacaoRepository.findByIdVariacaoMarketplace(idVariacaoMarketplace);
    }

    public ProdutoVariacao salvar(ProdutoVariacao produtoVariacao) {
        return produtoVariacaoRepository.save(produtoVariacao);
    }

    public List<ProdutoVariacao> buscarPorProduto(Long produtoId) {
        return produtoVariacaoRepository.findByProdutoId(produtoId);
    }
}
