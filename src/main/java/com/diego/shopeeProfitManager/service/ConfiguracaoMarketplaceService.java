package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.dto.ConfiguracaoMarketplaceRequestDTO;
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
            ConfiguracaoMarketplaceRequestDTO dto) {

        Optional<ConfiguracaoMarketplace> configuracaoExistente =
                configuracaoMarketplaceRepository.findByMesAndAno(mes, ano);

        if (configuracaoExistente.isPresent()) {
            ConfiguracaoMarketplace existente = configuracaoExistente.get();

            existente.setTaxaPercentual(dto.getTaxaPercentual());
            existente.setTaxaFixaPorPedido(dto.getTaxaFixaPorPedido());

            return configuracaoMarketplaceRepository.save(existente);
        }

        ConfiguracaoMarketplace configuracao = new ConfiguracaoMarketplace();

        configuracao.setMes(mes);
        configuracao.setAno(ano);
        configuracao.setTaxaFixaPorPedido(dto.getTaxaFixaPorPedido());
        configuracao.setTaxaPercentual(dto.getTaxaPercentual());

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
