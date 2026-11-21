package com.diego.shopeeProfitManager.repository;

import com.diego.shopeeProfitManager.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
