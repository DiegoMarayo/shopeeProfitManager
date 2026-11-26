package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.FechamentoMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FechamentoMensalRepository extends JpaRepository<FechamentoMensal, Long> {

    Optional<FechamentoMensal> findByMesAndAno(Integer mes, Integer ano);

    List<FechamentoMensal> findAllByOrderByAnoDescMesDesc();
}
