package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.dto.CustoProdutoImportacaoDTO;
import com.diego.shopeeProfitManager.dto.CustoProdutoRequestDTO;
import com.diego.shopeeProfitManager.service.CustoProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.diego.shopeeProfitManager.model.CustoProduto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/custos")
@Validated
public class CustoProdutoController {

    private final CustoProdutoService custoProdutoService;

    public CustoProdutoController(CustoProdutoService custoProdutoService) {
        this.custoProdutoService = custoProdutoService;
    }

    @PostMapping("/{produtoId}/{mes}/{ano}")
    public CustoProduto cadastrarCusto(
            @PathVariable @Positive(message = "O ID do produto deve ser positivo") Long produtoId,
            @PathVariable @Min(value = 1, message = "O mês deve ser entre 1 e 12.")
                @Max(value = 12, message = "O mês deve ser entre 1 e 12.") Integer mes,
            @PathVariable @Positive(message = "O ano deve ser positivo") Integer ano,
            @RequestBody @Valid CustoProdutoRequestDTO dto) {

        return custoProdutoService.cadastrarOuAtualizarCusto(produtoId, mes, ano, dto);
    }

    @GetMapping("/{produtoId}/{mes}/{ano}")
    public CustoProduto buscarCusto(
            @PathVariable @Positive(message = "O ID do produto deve ser positivo") Long produtoId,
            @PathVariable @Min(value = 1, message = "O mês deve ser entre 1 e 12.")
            @Max(value = 12, message = "O mês deve ser entre 1 e 12.") Integer mes,
            @PathVariable @Positive(message = "O ano deve ser positivo") Integer ano ) {

        return custoProdutoService.buscarCustoPorProdutoEPeriodo(produtoId, mes, ano);
    }

    @GetMapping("/{produtoId}/{mes}/{ano}/unitario")
    public BigDecimal calcularCustoUnitario(
            @PathVariable @Positive(message = "O ID do produto deve ser positivo") Long produtoId,
            @PathVariable @Min(value = 1, message = "O mês deve ser entre 1 e 12.")
            @Max(value = 12, message = "O mês deve ser entre 1 e 12.") Integer mes,
            @PathVariable @Positive(message = "O ano deve ser positivo") Integer ano) {

        return custoProdutoService.calcularCustoTotalUnitario(produtoId, mes, ano);
    }

    @PostMapping("/importacao/{mes}/{ano}")
    public List<CustoProduto> importarCustoEmLote(
            @PathVariable @Min(value = 1, message = "O mês deve ser entre 1 e 12.")
            @Max(value = 12, message = "O mês deve ser entre 1 e 12.") Integer mes,
            @PathVariable @Positive(message = "O ano deve ser positivo") Integer ano,
            @RequestBody List<@Valid CustoProdutoImportacaoDTO> custos) {

        return custoProdutoService.importarCustosEmLote(custos, mes, ano);
    }

    @GetMapping("/{produtoId}/{mes}/{ano}/mensal")
    public BigDecimal calcularCustoTotalMensal(
            @PathVariable @Positive(message = "O ID do produto deve ser positivo") Long produtoId,
            @PathVariable @Min(value = 1, message = "O mês deve ser entre 1 e 12.")
            @Max(value = 12, message = "O mês deve ser entre 1 e 12.") Integer mes,
            @PathVariable @Positive(message = "O ano deve ser positivo") Integer ano) {

        return custoProdutoService.calcularCustoTotalMensal(produtoId, mes, ano);
    }

}
