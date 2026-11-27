package com.diego.shopeeProfitManager.controller;

import com.diego.shopeeProfitManager.dto.ProdutoRequestDTO;
import com.diego.shopeeProfitManager.model.Produto;
import com.diego.shopeeProfitManager.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Validated
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarProduto() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoRequestDTO dto) {

        Produto produtoSalvo = produtoService.cadastrarProduto(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable @Positive(message = "O ID deve ser positivo.") Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable @Positive(message = "O ID deve ser positivo.")
                                                        Long id, @RequestBody @Valid ProdutoRequestDTO dto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, dto);

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable @Positive(message = "O ID deve ser positivo.") Long id) {
        produtoService.excluirProduto(id);

        return ResponseEntity.noContent().build();
    }
}
