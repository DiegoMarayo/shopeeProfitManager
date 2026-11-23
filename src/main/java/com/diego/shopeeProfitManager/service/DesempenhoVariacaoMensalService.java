package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.model.DesempenhoVariacaoMensal;
import com.diego.shopeeProfitManager.model.ProdutoVariacao;
import com.diego.shopeeProfitManager.repository.DesempenhoVariacaoMensalRepository;
import com.diego.shopeeProfitManager.repository.ProdutoVariacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DesempenhoVariacaoMensalService {

    private final DesempenhoVariacaoMensalRepository desempenhoVariacaoMensalRepository;
    private final ProdutoVariacaoRepository produtoVariacaoRepository;

    public DesempenhoVariacaoMensalService(DesempenhoVariacaoMensalRepository desempenhoVariacaoMensalRepository, ProdutoVariacaoRepository produtoVariacaoRepository) {
        this.desempenhoVariacaoMensalRepository = desempenhoVariacaoMensalRepository;
        this.produtoVariacaoRepository = produtoVariacaoRepository;
    }

    public void salvarOuAtualizarDesempenhoVariacaoMensal(
            Long idVariacaoMarketplace,
            Integer mes,
            Integer ano,
            BigDecimal vendas,
            Integer qtdVendidos
    ) {
        ProdutoVariacao produtoVariacao = produtoVariacaoRepository
                .findByIdVariacaoMarketplace(idVariacaoMarketplace)
                .orElseThrow(() -> new RuntimeException("Variação não encontrada"));

        DesempenhoVariacaoMensal desempenho = desempenhoVariacaoMensalRepository
                .findByProdutoVariacao_IdVariacaoMarketplaceAndMesAndAno(
                        idVariacaoMarketplace, mes, ano)
                .orElse(new DesempenhoVariacaoMensal());

        desempenho.setMes(mes);
        desempenho.setAno(ano);
        desempenho.setVendas(vendas);
        desempenho.setQtdVendidos(qtdVendidos);
        desempenho.setProdutoVariacao(produtoVariacao);

        desempenhoVariacaoMensalRepository.save(desempenho);
    }

}
