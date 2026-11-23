package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.DesempenhoVariacaoMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesempenhoVariacaoMensalRepository extends JpaRepository<DesempenhoVariacaoMensal, Long> {

    Optional<DesempenhoVariacaoMensal> findByProdutoVariacao_IdVariacaoMarketplaceAndMesAndAno(
            Long idVariacaoMarketplace, Integer mes, Integer ano);

}
