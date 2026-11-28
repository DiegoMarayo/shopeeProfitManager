package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.dto.DespesaAdsMensalRequestDTO;
import com.diego.shopeeProfitManager.model.DespesaAdsMensal;
import com.diego.shopeeProfitManager.repository.DespesaAdsMensalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DespesaAdsMensalService {

    private final DespesaAdsMensalRepository despesaAdsMensalRepository;

    public DespesaAdsMensalService(DespesaAdsMensalRepository despesaAdsMensalRepository) {
        this.despesaAdsMensalRepository = despesaAdsMensalRepository;
    }


    public DespesaAdsMensal cadastrarOuAtualizar(
            Integer mes,
            Integer ano,
            DespesaAdsMensalRequestDTO dto) {

        Optional<DespesaAdsMensal> despesaAdsMensalExistente =
                despesaAdsMensalRepository.findByMesAndAno(mes, ano);

        if (despesaAdsMensalExistente.isPresent()) {

            DespesaAdsMensal existente = despesaAdsMensalExistente.get();

            existente.setAdsMensal(dto.getAdsMensal());

            return despesaAdsMensalRepository.save(existente);

        }

        DespesaAdsMensal adsMensal = new DespesaAdsMensal();

        adsMensal.setMes(mes);
        adsMensal.setAno(ano);
        adsMensal.setAdsMensal(dto.getAdsMensal());

        return despesaAdsMensalRepository.save(adsMensal);

    }

    public DespesaAdsMensal buscarPorPeriodo(Integer mes, Integer ano) {

        return despesaAdsMensalRepository.findByMesAndAno(mes, ano)
                .orElseThrow(() -> new RuntimeException(
                        "Despesa de Ads não encontrado para o periodo informado"));
    }

    public List<DespesaAdsMensal> buscarHistorico() {

        return despesaAdsMensalRepository.findByOrderByAnoDescMesDesc();
    }

}
