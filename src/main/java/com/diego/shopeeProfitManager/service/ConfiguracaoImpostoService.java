package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.dto.ConfiguracaoImpostoRequestDTO;
import com.diego.shopeeProfitManager.model.ConfiguracaoImposto;
import com.diego.shopeeProfitManager.repository.ConfiguracaoImpostoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracaoImpostoService {

    private final ConfiguracaoImpostoRepository configuracaoImpostoRepository;

    public ConfiguracaoImpostoService(ConfiguracaoImpostoRepository configuracaoImpostoRepository) {
        this.configuracaoImpostoRepository = configuracaoImpostoRepository;
    }

    public ConfiguracaoImposto cadastrarOuAtualizar(
            Integer mes,
            Integer ano,
            ConfiguracaoImpostoRequestDTO dto) {

        Optional<ConfiguracaoImposto> configuracaoExistente =
                configuracaoImpostoRepository.findByMesAndAno(mes, ano);

        if (configuracaoExistente.isPresent()) {

            ConfiguracaoImposto existente = configuracaoExistente.get();

            existente.setImpostoPercentual(dto.getImpostoPercentual());

            return configuracaoImpostoRepository.save(existente);
        }

        ConfiguracaoImposto configuracao = new ConfiguracaoImposto();

        configuracao.setMes(mes);
        configuracao.setAno(ano);
        configuracao.setImpostoPercentual(dto.getImpostoPercentual());

        return configuracaoImpostoRepository.save(configuracao);
    }

    public ConfiguracaoImposto buscarPorPeriodo(Integer mes, Integer ano) {

        return  configuracaoImpostoRepository.findByMesAndAno(mes, ano)
                .orElseThrow(()-> new RuntimeException(
                        "Configuracao de imposto não encontrada para o periodo informado"));

    }

    public List<ConfiguracaoImposto> buscarHistorico() {
        return configuracaoImpostoRepository.findAllByOrderByAnoDescMesDesc();
    }

}

