package com.example.tapAp2.controller;

import com.example.tapAp2.dao.ProdutoDao;
import com.example.tapAp2.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoDao dao =
            new ProdutoDao();

    @GetMapping
    public List<Produto> listarTodos() {
        return dao.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return dao.buscarPorId(id);
    }

    @PostMapping
    public String inserir(@RequestBody Produto produto) {
        dao.inserir(produto);
        return "Produto inserido com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestBody Produto produto) {

        produto.setId(id);

        dao.atualizar(produto);

        return "Produto atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {

        dao.deletar(id);

        return "Produto deletado com sucesso!";
    }
}