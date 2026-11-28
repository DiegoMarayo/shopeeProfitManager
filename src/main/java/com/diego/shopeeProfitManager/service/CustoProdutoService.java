package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.dto.CustoProdutoImportacaoDTO;
import com.diego.shopeeProfitManager.dto.CustoProdutoRequestDTO;
import com.diego.shopeeProfitManager.exception.ProdutoNaoEncontradoException;
import com.diego.shopeeProfitManager.model.CustoProduto;
import com.diego.shopeeProfitManager.model.Produto;
import com.diego.shopeeProfitManager.repository.CustoProdutoRepository;
import com.diego.shopeeProfitManager.repository.DesempenhoMensalRepository;
import com.diego.shopeeProfitManager.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@Service
public class CustoProdutoService {

    private final CustoProdutoRepository custoProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final DesempenhoMensalRepository desempenhoMensalRepository;

    public CustoProdutoService(CustoProdutoRepository custoProdutoRepository, ProdutoRepository produtoRepository,
                               DesempenhoMensalRepository desempenhoMensalRepository) {
        this.custoProdutoRepository = custoProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.desempenhoMensalRepository = desempenhoMensalRepository;
    }

    public CustoProduto cadastrarOuAtualizarCusto(
            Long produtoId, Integer mes, Integer ano, CustoProdutoRequestDTO dto) {

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        CustoProduto custoExistente = custoProdutoRepository.findByProdutoIdAndMesAndAno(produtoId, mes, ano)
                .orElse(new CustoProduto());

        custoExistente.setProduto(produto);
        custoExistente.setMes(mes);
        custoExistente.setAno(ano);
        custoExistente.setCustoProduto(dto.getCustoProduto());
        custoExistente.setCustoEmbalagem(dto.getCustoEmbalagem());
        custoExistente.setDespesasExtras(dto.getDespesasExtras());

        return custoProdutoRepository.save(custoExistente);

    }

    public CustoProduto buscarCustoPorProdutoEPeriodo(Long produtoId, Integer mes, Integer ano) {

        return custoProdutoRepository.findByProdutoIdAndMesAndAno(produtoId, mes, ano)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(
                        "Custo do produto não encontrado para o período informado"));
    }

    public List<CustoProduto> buscarHistoricoCustoPorProduto(Long produtoId) {

        produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        return custoProdutoRepository.findByProdutoIdOrderByAnoDescMesDesc(produtoId);
    }

    public BigDecimal calcularCustoTotalUnitario(Long produtoId, Integer mes, Integer ano) {

        CustoProduto custo = buscarCustoPorProdutoEPeriodo(produtoId, mes, ano);

        return custo.getCustoProduto().add(custo.getCustoEmbalagem()).add(custo.getDespesasExtras());
    }

    public BigDecimal calcularCustoTotalMensal(Long produtoId, Integer mes, Integer ano) {

        BigDecimal custoUnitario = calcularCustoTotalUnitario(produtoId, mes, ano);

        Integer quantidadeVendida = desempenhoMensalRepository
                .findByProduto_IdAndMesReferenciaAndAnoReferencia(
                produtoId, mes, ano)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(
                        "Desempenho mensal não encontrado para o produto")).getQtdVendidos();

        return  custoUnitario.multiply(BigDecimal.valueOf(quantidadeVendida));
    }

    public List<CustoProduto> importarCustosEmLote(
            List<CustoProdutoImportacaoDTO> custos,
            Integer mes,
            Integer ano) {

        List<CustoProduto> custoImportados = new ArrayList<>();

        for (CustoProdutoImportacaoDTO dto : custos) {

            CustoProdutoRequestDTO custoDTO = new CustoProdutoRequestDTO();

            custoDTO.setCustoProduto(dto.getCustoProduto());
            custoDTO.setCustoEmbalagem(dto.getCustoEmbalagem());
            custoDTO.setDespesasExtras(dto.getDespesasExtras());

            CustoProduto custoSalvo = cadastrarOuAtualizarCusto(
                    dto.getProdutoId(),
                    mes,
                    ano,
                    custoDTO);

            custoImportados.add(custoSalvo);
        }
        return  custoImportados;
    }

}
