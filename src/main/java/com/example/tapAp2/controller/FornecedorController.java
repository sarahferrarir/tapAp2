package com.example.tapAp2.controller;

import com.example.tapAp2.dao.FornecedorDao;
import com.example.tapAp2.factory.DaoFactory;
import com.example.tapAp2.model.Fornecedor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorDao fornecedorDao =
            DaoFactory.criarDao(FornecedorDao.class);

    @GetMapping
    public List<Fornecedor> listarTodos() {
        return fornecedorDao.listarTodos();
    }

    @GetMapping("/{id}")
    public Fornecedor buscarPorId(@PathVariable Long id) {
        return fornecedorDao.buscarPorId(id);
    }

    @PostMapping
    public String inserir(@RequestBody Fornecedor fornecedor) {
        fornecedorDao.inserir(fornecedor);
        return "Fornecedor inserido com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestBody Fornecedor fornecedor) {
        fornecedor.setId(id);
        fornecedorDao.atualizar(fornecedor);
        return "Fornecedor atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        fornecedorDao.deletar(id);
        return "Fornecedor deletado com sucesso!";
    }
}