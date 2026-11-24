package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.CustoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface CustoProdutoRepository extends JpaRepository<CustoProduto, Long> {

    Optional<CustoProduto> findByProdutoIdAndMesAndAno(Long produtoId, Integer mes, Integer ano);

    List<CustoProduto> findByProdutoIdOrderByAnoDescMesDesc(Long produtoId);
}
