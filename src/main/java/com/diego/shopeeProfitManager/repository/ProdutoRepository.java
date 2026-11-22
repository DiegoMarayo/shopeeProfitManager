package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByIdProdutoMarketplace(Long idProdutoMarketplace);

}
