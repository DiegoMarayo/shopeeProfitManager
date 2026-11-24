package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.DespesaAdsMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DespesaAdsMensalRepository extends JpaRepository<DespesaAdsMensal, Long> {

    Optional<DespesaAdsMensal> findByMesAndAno(Integer mes, Integer ano);

    List<DespesaAdsMensal> findByOrderByAnoDescMesDesc();
}
