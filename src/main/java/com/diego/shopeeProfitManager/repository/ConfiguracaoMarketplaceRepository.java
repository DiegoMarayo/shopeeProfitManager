package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.ConfiguracaoMarketplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConfiguracaoMarketplaceRepository extends JpaRepository<ConfiguracaoMarketplace, Long> {

    Optional<ConfiguracaoMarketplace> findByMesAndAno(Integer mes, Integer ano);

    List<ConfiguracaoMarketplace> findAllByOrderByAnoDescMesDesc();

}
