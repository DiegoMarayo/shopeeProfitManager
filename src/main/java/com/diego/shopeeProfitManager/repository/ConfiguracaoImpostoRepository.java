package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.ConfiguracaoImposto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfiguracaoImpostoRepository extends JpaRepository<ConfiguracaoImposto, Long> {

    Optional<ConfiguracaoImposto> findByMesAndAno(Integer mes, Integer ano);

    List<ConfiguracaoImposto> findAllByOrderByAnoDescMesDesc();
}
