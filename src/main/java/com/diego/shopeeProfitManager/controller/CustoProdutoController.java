package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.dto.CustoProdutoImportacaoDTO;
import com.diego.shopeeProfitManager.service.CustoProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.diego.shopeeProfitManager.model.CustoProduto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/custos")
public class CustoProdutoController {

    private final CustoProdutoService custoProdutoService;

    public CustoProdutoController(CustoProdutoService custoProdutoService) {
        this.custoProdutoService = custoProdutoService;
    }

    @PostMapping("/{produtoId}/{mes}/{ano}")
    public CustoProduto cadastrarCusto(
            @PathVariable Long produtoId,
            @PathVariable Integer mes,
            @PathVariable Integer ano,
            @RequestBody CustoProduto custoProduto ) {

        return custoProdutoService.cadastrarOuAtualizarCusto(produtoId, mes, ano, custoProduto);
    }

    @GetMapping("/{produtoId}/{mes}/{ano}")
    public CustoProduto buscarCusto(
            @PathVariable Long produtoId,
            @PathVariable Integer mes,
            @PathVariable Integer ano ) {

        return custoProdutoService.buscarCustoPorProdutoEPeriodo(produtoId, mes, ano);
    }

    @GetMapping("/{produtoId}/{mes}/{ano}/unitario")
    public BigDecimal calcularCustoUnitario(
            @PathVariable Long produtoId,
            @PathVariable Integer mes,
            @PathVariable Integer ano) {

        return custoProdutoService.calcularCustoTotalUnitario(produtoId, mes, ano);
    }

    @PostMapping("/importacao/{mes}/{ano}")
    public List<CustoProduto> importarCustoEmLote(
            @PathVariable Integer mes,
            @PathVariable Integer ano,
            @RequestBody List<@Valid CustoProdutoImportacaoDTO> custos) {

        return custoProdutoService.importarCustosEmLote(custos, mes, ano);
    }

    @GetMapping("/{produtoId}/{mes}/{ano}/mensal")
    public BigDecimal calcularCustoTotalMensal(
            @PathVariable Long produtoId,
            @PathVariable Integer mes,
            @PathVariable Integer ano) {

        return custoProdutoService.calcularCustoTotalMensal(produtoId, mes, ano);
    }

}
