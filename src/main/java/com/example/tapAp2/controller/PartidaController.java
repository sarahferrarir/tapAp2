package com.example.tapAp2.controller;

import com.example.tapAp2.dao.PartidaDao;
import com.example.tapAp2.factory.DaoFactory;
import com.example.tapAp2.model.Partida;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaDao partidaDao =
            DaoFactory.criarDao(PartidaDao.class);

    @GetMapping
    public List<Partida> listarTodos() {
        return partidaDao.listarTodos();
    }

    @GetMapping("/{id}")
    public Partida buscarPorId(@PathVariable Long id) {
        return partidaDao.buscarPorId(id);
    }

    @PostMapping
    public String inserir(@RequestBody Partida partida) {
        partidaDao.inserir(partida);
        return "Partida inserida com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestBody Partida partida) {

        partida.setId(id);

        partidaDao.atualizar(partida);

        return "Partida atualizada com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {

        partidaDao.deletar(id);

        return "Partida deletada com sucesso!";
    }
}