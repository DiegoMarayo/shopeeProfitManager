package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.model.DespesaOperacional;
import com.diego.shopeeProfitManager.service.DespesaOperacionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesas/operacionais")
public class DespesaOperacionalController {

    private final DespesaOperacionalService despesaOperacionalService;

    public DespesaOperacionalController(DespesaOperacionalService despesaOperacionalService) {
        this.despesaOperacionalService = despesaOperacionalService;
    }

    @PostMapping("/{mes}/{ano}")
    public DespesaOperacional cadastrar(
            @PathVariable Integer mes,
            @PathVariable Integer ano,
            @RequestBody DespesaOperacional despesaOperacional) {

        return despesaOperacionalService.cadastrar(mes, ano, despesaOperacional);

    }

    @GetMapping("/{mes}/{ano}")
    public List<DespesaOperacional> buscarPorPeriodo(
            @PathVariable Integer mes,
            @PathVariable Integer ano) {

        return despesaOperacionalService.buscarPorPeriodo(mes, ano);
    }

    @GetMapping("/historico")

    public List<DespesaOperacional> buscarHistorico() {

        return despesaOperacionalService.buscarHistorico();
    }

    @DeleteMapping("/{id}")
    public void excluirDespesa(@PathVariable Long id) {

        despesaOperacionalService.excluir(id);
    }

}
