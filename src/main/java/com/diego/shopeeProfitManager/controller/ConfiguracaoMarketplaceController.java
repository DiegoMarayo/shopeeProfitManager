package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.model.ConfiguracaoMarketplace;
import com.diego.shopeeProfitManager.service.ConfiguracaoMarketplaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config/marketplace")
public class ConfiguracaoMarketplaceController {

    private final ConfiguracaoMarketplaceService configuracaoMarketplaceService;

    public ConfiguracaoMarketplaceController(ConfiguracaoMarketplaceService configuracaoMarketplaceService) {
        this.configuracaoMarketplaceService = configuracaoMarketplaceService;
    }

    @PostMapping("/{mes}/{ano}")
    public ConfiguracaoMarketplace cadastrarOuAtualizar(
            @PathVariable Integer mes,
            @PathVariable Integer ano,
            @RequestBody ConfiguracaoMarketplace configuracao) {

        return configuracaoMarketplaceService.cadastrarOuAtualizar(mes, ano, configuracao);

    }

    @GetMapping("/{mes}/{ano}")
    public ConfiguracaoMarketplace buscarConfiguracao(
            @PathVariable Integer mes,
            @PathVariable Integer ano
    ) {
        return configuracaoMarketplaceService.buscarPorPeriodo(mes, ano);
    }

    @GetMapping("/historico")
    public List<ConfiguracaoMarketplace> buscarHistorico() {
        return configuracaoMarketplaceService.buscarHistorico();
    }

}
