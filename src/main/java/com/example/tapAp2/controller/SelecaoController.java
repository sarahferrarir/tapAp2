package com.example.tapAp2.controller;

import com.example.tapAp2.dao.SelecaoDao;
import com.example.tapAp2.factory.DaoFactory;
import com.example.tapAp2.model.Selecao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selecoes")
public class SelecaoController {

    private final SelecaoDao selecaoDao =
            DaoFactory.criarDao(SelecaoDao.class);

    @GetMapping
    public List<Selecao> listarTodos() {
        return selecaoDao.listarTodos();
    }

    @GetMapping("/{id}")
    public Selecao buscarPorId(@PathVariable Long id) {
        return selecaoDao.buscarPorId(id);
    }

    @PostMapping
    public String inserir(@RequestBody Selecao selecao) {
        selecaoDao.inserir(selecao);
        return "Seleção inserida com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestBody Selecao selecao) {

        selecao.setId(id);

        selecaoDao.atualizar(selecao);

        return "Seleção atualizada com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {

        selecaoDao.deletar(id);

        return "Seleção deletada com sucesso!";
    }
}