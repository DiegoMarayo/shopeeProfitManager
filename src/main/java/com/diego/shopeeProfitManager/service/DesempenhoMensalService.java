package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.model.DesempenhoMensal;
import com.diego.shopeeProfitManager.model.Produto;
import com.diego.shopeeProfitManager.repository.DesempenhoMensalRepository;
import com.diego.shopeeProfitManager.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DesempenhoMensalService {

    private final DesempenhoMensalRepository desempenhoMensalRepository;
    private final ProdutoRepository produtoRepository;

    public DesempenhoMensalService(DesempenhoMensalRepository desempenhoMensalRepository, ProdutoRepository produtoRepository) {
        this.desempenhoMensalRepository = desempenhoMensalRepository;
        this.produtoRepository = produtoRepository;
    }

    public Optional<DesempenhoMensal> buscarPorProdutoMesAno(Long produtoId, Integer mes, Integer ano) {

        return desempenhoMensalRepository.findByProduto_IdAndMesReferenciaAndAnoReferencia(produtoId, mes, ano);
    }

    public DesempenhoMensal salvar(DesempenhoMensal desempenhoMensal) {
        return desempenhoMensalRepository.save(desempenhoMensal);
    }

    public Produto buscarProdutoPorIdMarketplace(Long idProdutoMarketplace) {
        return produtoRepository.findByIdProdutoMarketplace(idProdutoMarketplace)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public DesempenhoMensal salvarOuAtualizarDesempenhoMensal(
           Long idProdutoMarketplace,
           Integer mes,
           Integer ano,
           BigDecimal vendasTotal,
           Integer qtdVendidos) {

        Produto produto = buscarProdutoPorIdMarketplace(idProdutoMarketplace);

        Optional<DesempenhoMensal> desempenhoMensal = buscarPorProdutoMesAno(produto.getId(), mes, ano);

        if (desempenhoMensal.isPresent()) {

            DesempenhoMensal existente = desempenhoMensal.get();

            existente.setVendasTotal(vendasTotal);
            existente.setQtdVendidos(qtdVendidos);

            return salvar(existente);

        } else {

            DesempenhoMensal novo = new DesempenhoMensal();

            novo.setProduto(produto);
            novo.setMesReferencia(mes);
            novo.setAnoReferencia(ano);
            novo.setVendasTotal(vendasTotal);
            novo.setQtdVendidos(qtdVendidos);

            return salvar(novo);
        }
    }

}
