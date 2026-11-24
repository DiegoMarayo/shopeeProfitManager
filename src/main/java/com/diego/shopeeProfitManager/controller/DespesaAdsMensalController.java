package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.model.DespesaAdsMensal;
import com.diego.shopeeProfitManager.service.DespesaAdsMensalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/ads")
public class DespesaAdsMensalController {

    private final DespesaAdsMensalService despesaAdsMensalService;

    public DespesaAdsMensalController(DespesaAdsMensalService despesaAdsMensalService) {
        this.despesaAdsMensalService = despesaAdsMensalService;
    }

    @PostMapping("/{mes}/{ano}")
    public DespesaAdsMensal cadastrarOuAtualizar(
            @PathVariable Integer mes,
            @PathVariable Integer ano,
            @RequestBody DespesaAdsMensal despesaAdsMensal) {

        return despesaAdsMensalService.cadastrarOuAtualizar(mes, ano, despesaAdsMensal);

    }

    @GetMapping("/{mes}/{ano}")
    public DespesaAdsMensal buscarPorPeriodo(
            @PathVariable Integer mes,
            @PathVariable Integer ano) {

        return despesaAdsMensalService.buscarPorPeriodo(mes, ano);
    }

    @GetMapping("/historico")
    public List<DespesaAdsMensal> buscarHistorico() {

        return despesaAdsMensalService.buscarHistorico();
    }
}
