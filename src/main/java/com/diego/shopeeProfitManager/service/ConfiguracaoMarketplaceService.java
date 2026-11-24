package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.model.ConfiguracaoMarketplace;
import com.diego.shopeeProfitManager.repository.ConfiguracaoMarketplaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoMarketplaceService {

    private final ConfiguracaoMarketplaceRepository configuracaoMarketplaceRepository;

    public ConfiguracaoMarketplaceService(ConfiguracaoMarketplaceRepository configuracaoMarketplaceRepository) {
        this.configuracaoMarketplaceRepository = configuracaoMarketplaceRepository;
    }

    public ConfiguracaoMarketplace cadastrarOuAtualizar(
            Integer mes,
            Integer ano,
            ConfiguracaoMarketplace configuracao) {

        Optional<ConfiguracaoMarketplace> configuracaoExistente =
                configuracaoMarketplaceRepository.findByMesAndAno(mes, ano);

        if (configuracaoExistente.isPresent()) {
            ConfiguracaoMarketplace existente = configuracaoExistente.get();

            existente.setTaxaPercentual(configuracao.getTaxaPercentual());
            existente.setTaxaFixaPorPedido(configuracao.getTaxaFixaPorPedido());

            return configuracaoMarketplaceRepository.save(existente);
        }

        configuracao.setMes(mes);
        configuracao.setAno(ano);

        return configuracaoMarketplaceRepository.save(configuracao);
    }

    public ConfiguracaoMarketplace buscarPorPeriodo(Integer mes, Integer ano) {

        return configuracaoMarketplaceRepository.findByMesAndAno(mes, ano)
                .orElseThrow(() -> new RuntimeException(
                        "Configuração do marketplace não encontrada para o período informado."
                ));
    }

    public List<ConfiguracaoMarketplace> buscarHistorico() {
        return configuracaoMarketplaceRepository.findAllByOrderByAnoDescMesDesc();
    }

}
