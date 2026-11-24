package com.diego.shopeeProfitManager.service;

import com.diego.shopeeProfitManager.model.DespesaOperacional;
import com.diego.shopeeProfitManager.repository.DespesaOperacionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaOperacionalService {

    private final DespesaOperacionalRepository despesaOperacionalRepository;

    public DespesaOperacionalService(DespesaOperacionalRepository despesaOperacionalRepository) {
        this.despesaOperacionalRepository = despesaOperacionalRepository;
    }

    public DespesaOperacional cadastrar(
            Integer mes,
            Integer ano,
            DespesaOperacional despesaOperacional) {

        despesaOperacional.setMes(mes);
        despesaOperacional.setAno(ano);

        return despesaOperacionalRepository.save(despesaOperacional);

    }

    public List<DespesaOperacional> buscarPorPeriodo(Integer mes, Integer ano) {

        return despesaOperacionalRepository.findByMesAndAno(mes, ano);
    }

    public List<DespesaOperacional> buscarHistorico() {

        return despesaOperacionalRepository.findAllByOrderByAnoDescMesDesc();
    }

    public void excluir(Long id) {

        despesaOperacionalRepository.deleteById(id);
    }

}



