package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.DespesaOperacional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaOperacionalRepository extends JpaRepository<DespesaOperacional, Long> {

    List<DespesaOperacional> findByMesAndAno(Integer mes, Integer ano);

    List<DespesaOperacional> findAllByOrderByAnoDescMesDesc();

}
