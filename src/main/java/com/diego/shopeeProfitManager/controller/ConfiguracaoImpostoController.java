package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.model.ConfiguracaoImposto;
import com.diego.shopeeProfitManager.service.ConfiguracaoImpostoService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/imposto")
@Validated
public class ConfiguracaoImpostoController {

    private final ConfiguracaoImpostoService configuracaoImpostoService;

    public ConfiguracaoImpostoController(ConfiguracaoImpostoService configuracaoImpostoService) {
        this.configuracaoImpostoService = configuracaoImpostoService;
    }

    @PostMapping("/{mes}/{ano}")
    public ConfiguracaoImposto cadastrarOuAtualizar(
            @PathVariable  Integer mes,
            @PathVariable  Integer ano,
            @RequestBody @Valid ConfiguracaoImposto configuracaoImposto) {

        return configuracaoImpostoService.cadastrarOuAtualizar(mes, ano, configuracaoImposto);

    }

    @GetMapping("/{mes}/{ano}")
    public ConfiguracaoImposto buscarPorPeriodo(
            @PathVariable  Integer mes,
            @PathVariable  Integer ano) {

        return configuracaoImpostoService.buscarPorPeriodo(mes, ano);

    }

    @GetMapping("/historico")
    public List<ConfiguracaoImposto> buscarHistorico() {

        return configuracaoImpostoService.buscarHistorico();
    }

}
