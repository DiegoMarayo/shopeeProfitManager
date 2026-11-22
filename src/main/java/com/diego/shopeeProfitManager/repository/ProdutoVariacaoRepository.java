package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.ProdutoVariacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoVariacaoRepository extends JpaRepository<ProdutoVariacao, Long> {

    Optional<ProdutoVariacao> findByIdVariacaoMarketplace(Long idVariacaoMarketplace);

    List<ProdutoVariacao> findByProdutoId(Long produtoId);
}
