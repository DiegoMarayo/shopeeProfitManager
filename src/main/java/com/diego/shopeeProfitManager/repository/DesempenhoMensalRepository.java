package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.DesempenhoMensal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesempenhoMensalRepository extends JpaRepository<DesempenhoMensal, Long> {

    Optional<DesempenhoMensal> findByProduto_IdAndMesReferenciaAndAnoReferencia(
            Long produtoId,
            Integer mesReferencia,
            Integer anoReferencia
    );
}
