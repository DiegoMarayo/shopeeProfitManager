package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.dto.ProdutoRequestDTO;
import com.diego.shopeeProfitManager.exception.ProdutoNaoEncontradoException;
import com.diego.shopeeProfitManager.model.Produto;
import com.diego.shopeeProfitManager.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto cadastrarProduto(ProdutoRequestDTO dto) {

        Produto produto = new Produto();
        produto.setIdProdutoMarketplace(dto.getIdProdutoMarketplace());
        produto.setNomeProduto(dto.getNomeProduto());

        return produtoRepository.save(produto);
    }

    public Produto buscarProdutoPorId (Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    public Produto atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        produtoExistente.setIdProdutoMarketplace(dto.getIdProdutoMarketplace());
        produtoExistente.setNomeProduto(dto.getNomeProduto());

        return produtoRepository.save(produtoExistente);
    }

    public void excluirProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }
}
