package com.example.tapAp2.controller;

import com.example.tapAp2.dao.ProdutoFornecedorDao;
import com.example.tapAp2.factory.DaoFactory;
import com.example.tapAp2.model.ProdutoFornecedor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto-fornecedor")
public class ProdutoFornecedorController {

    private final ProdutoFornecedorDao produtoFornecedorDao =
            DaoFactory.criarDao(ProdutoFornecedorDao.class);

    @GetMapping
    public List<ProdutoFornecedor> listarTodos() {
        return produtoFornecedorDao.listarTodos();
    }

    @GetMapping("/produto/{produtoId}")
    public List<ProdutoFornecedor> buscarPorProduto(@PathVariable Long produtoId) {
        return produtoFornecedorDao.buscarPorProduto(produtoId);
    }

    @PostMapping
    public String inserir(@RequestBody ProdutoFornecedor produtoFornecedor) {
        produtoFornecedorDao.inserir(produtoFornecedor);
        return "Vínculo inserido com sucesso!";
    }

    @DeleteMapping("/{produtoId}/{fornecedorId}")
    public String deletar(@PathVariable Long produtoId,
                          @PathVariable Long fornecedorId) {
        produtoFornecedorDao.deletar(produtoId, fornecedorId);
        return "Vínculo deletado com sucesso!";
    }
}