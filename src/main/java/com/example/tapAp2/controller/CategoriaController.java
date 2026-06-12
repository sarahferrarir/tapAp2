package com.example.tapAp2.controller;

import com.example.tapAp2.dao.CategoriaDao;
import com.example.tapAp2.factory.DaoFactory;
import com.example.tapAp2.model.Categoria;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaDao categoriaDao =
            DaoFactory.criarDao(CategoriaDao.class);

    @GetMapping
    public List<Categoria> listarTodos() {
        return categoriaDao.listarTodos();
    }

    @GetMapping("/{id}")
    public Categoria buscarPorId(@PathVariable Long id) {
        return categoriaDao.buscarPorId(id);
    }

    @PostMapping
    public String inserir(@RequestBody Categoria categoria) {
        categoriaDao.inserir(categoria);
        return "Categoria inserida com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestBody Categoria categoria) {
        categoria.setId(id);
        categoriaDao.atualizar(categoria);
        return "Categoria atualizada com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        categoriaDao.deletar(id);
        return "Categoria deletada com sucesso!";
    }
}