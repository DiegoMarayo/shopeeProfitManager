package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.model.DesempenhoMensal;

import com.diego.shopeeProfitManager.model.DespesaOperacional;
import com.diego.shopeeProfitManager.model.FechamentoMensal;
import com.diego.shopeeProfitManager.repository.DesempenhoMensalRepository;
import com.diego.shopeeProfitManager.repository.FechamentoMensalRepository;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class FechamentoMensalService {

    private final FechamentoMensalRepository fechamentoMensalRepository;
    private final DesempenhoMensalRepository desempenhoMensalRepository;
    private final CustoProdutoService custoProdutoService;
    private final ConfiguracaoMarketplaceService configuracaoMarketplaceService;
    private final ConfiguracaoImpostoService configuracaoImpostoService;
    private final DespesaAdsMensalService despesaAdsMensalService;
    private final DespesaOperacionalService despesaOperacionalService;

    public FechamentoMensalService(FechamentoMensalRepository fechamentoMensalRepository,
                                   DesempenhoMensalRepository desempenhoMensalRepository,
                                   CustoProdutoService custoProdutoService,
                                   ConfiguracaoMarketplaceService configuracaoMarketplaceService,
                                   ConfiguracaoImpostoService configuracaoImpostoService,
                                   DespesaAdsMensalService despesaAdsMensalService,
                                   DespesaOperacionalService despesaOperacionalService) {
        this.fechamentoMensalRepository = fechamentoMensalRepository;
        this.desempenhoMensalRepository = desempenhoMensalRepository;
        this.custoProdutoService = custoProdutoService;
        this.configuracaoMarketplaceService = configuracaoMarketplaceService;
        this.configuracaoImpostoService = configuracaoImpostoService;
        this.despesaAdsMensalService = despesaAdsMensalService;
        this.despesaOperacionalService = despesaOperacionalService;
    }

    public BigDecimal calcularFaturamentoBruto(Integer mes, Integer ano) {

        return desempenhoMensalRepository.findByMesReferenciaAndAnoReferencia(mes, ano)
                .stream()
                .map(desempenho ->  desempenho.getVendasTotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Integer calcularUnidadesVendidas(Integer mes, Integer ano) {

        return desempenhoMensalRepository.findByMesReferenciaAndAnoReferencia(mes, ano)
                .stream()
                .map(DesempenhoMensal::getQtdVendidos)
                .reduce(0, Integer::sum);
    }

    public BigDecimal calcularCustoProdutos(Integer mes, Integer ano) {

        return desempenhoMensalRepository.findByMesReferenciaAndAnoReferencia(mes, ano)
                .stream()
                .filter(desempenho -> desempenho.getQtdVendidos() > 0)
                .map(desempenho -> custoProdutoService.calcularCustoTotalMensal(
                        desempenho.getProduto().getId(), mes, ano
                ))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularTaxaMarketplacePercentual(Integer mes, Integer ano) {

        BigDecimal faturamentoBruto = calcularFaturamentoBruto(mes, ano);

        BigDecimal taxaPercentual = configuracaoMarketplaceService.buscarPorPeriodo(mes, ano).getTaxaPercentual();

        return faturamentoBruto.multiply(taxaPercentual).divide(BigDecimal.valueOf(100));
    }

    public BigDecimal calcularTaxaFixaMarketplace(Integer mes, Integer ano) {

        Integer unidadesVendidas = calcularUnidadesVendidas(mes, ano);

        BigDecimal taxaFixa = configuracaoMarketplaceService.buscarPorPeriodo(mes, ano)
                .getTaxaFixaPorPedido();

        return taxaFixa.multiply(BigDecimal.valueOf(unidadesVendidas));
    }

    public BigDecimal calcularImposto(Integer mes, Integer ano) {

        BigDecimal faturamentoBruto = calcularFaturamentoBruto(mes, ano);

        BigDecimal impostoPercentual = configuracaoImpostoService.buscarPorPeriodo(mes, ano).getImpostoPercentual();

        return faturamentoBruto.multiply(impostoPercentual).divide(BigDecimal.valueOf(100));
    }

    public BigDecimal buscarDespesaAds(Integer mes, Integer ano) {

        return despesaAdsMensalService.buscarPorPeriodo(mes, ano).getAdsMensal();
    }

    public BigDecimal calcularDespesasOperacionais(Integer mes, Integer ano) {

        return despesaOperacionalService.buscarPorPeriodo(mes, ano)
                .stream()
                .map(DespesaOperacional::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calcularLucroLiquido(Integer mes, Integer ano) {

        BigDecimal faturamentoBruto = calcularFaturamentoBruto(mes, ano);
        BigDecimal custoProdutos = calcularCustoProdutos(mes, ano);
        BigDecimal taxaMarketplacePorcentual = calcularTaxaMarketplacePercentual(mes, ano);
        BigDecimal taxaFixaMarketplace = calcularTaxaFixaMarketplace(mes, ano);
        BigDecimal impostos = calcularImposto(mes, ano);
        BigDecimal ads = buscarDespesaAds(mes, ano);
        BigDecimal despesasOperacionais = calcularDespesasOperacionais(mes, ano);

        return faturamentoBruto
                .subtract(custoProdutos)
                .subtract(taxaMarketplacePorcentual)
                .subtract(taxaFixaMarketplace)
                .subtract(impostos)
                .subtract(ads)
                .subtract(despesasOperacionais);
    }

    public BigDecimal calcularMargemLiquida(Integer mes, Integer ano) {

        BigDecimal lucroLiquido = calcularLucroLiquido(mes, ano);
        BigDecimal faturamento = calcularFaturamentoBruto(mes, ano);

        if (faturamento.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return lucroLiquido.multiply(BigDecimal.valueOf(100))
                .divide(faturamento, 2, RoundingMode.HALF_UP);
    }

    public FechamentoMensal gerarFechamentoMensal(Integer mes, Integer ano) {

        FechamentoMensal fechamento = fechamentoMensalRepository
                .findByMesAndAno(mes, ano)
                .orElse(new FechamentoMensal());

        fechamento.setMes(mes);
        fechamento.setAno(ano);

        fechamento.setFaturamentoBruto(calcularFaturamentoBruto(mes, ano));
        fechamento.setUnidadesVendidas(calcularUnidadesVendidas(mes, ano));
        fechamento.setCustoProdutos(calcularCustoProdutos(mes, ano));

        fechamento.setTaxaMarketplacePercentual(calcularTaxaMarketplacePercentual(mes, ano));
        fechamento.setTaxaMarketplaceFixa(calcularTaxaFixaMarketplace(mes, ano));

        fechamento.setImpostos(calcularImposto(mes, ano));
        fechamento.setAds(buscarDespesaAds(mes, ano));
        fechamento.setDespesasOperacionais(calcularDespesasOperacionais(mes, ano));

        fechamento.setLucroLiquido(calcularLucroLiquido(mes, ano));
        fechamento.setMargemLiquida(calcularMargemLiquida(mes, ano));

        return fechamentoMensalRepository.save(fechamento);
    }

    public FechamentoMensal buscarFechamentoPorPeriodo(Integer mes, Integer ano) {

        return fechamentoMensalRepository.findByMesAndAno(mes, ano)
                .orElseThrow(() -> new RuntimeException("Fechamento mensal não encontrado."));
    }

    public List<FechamentoMensal> buscarHistoricoFechamentos() {

        return fechamentoMensalRepository.findAllByOrderByAnoDescMesDesc();

    }

}

