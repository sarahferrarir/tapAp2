package com.example.tapAp2.controller;

import com.example.tapAp2.dao.PartidaSelecaoDao;
import com.example.tapAp2.factory.DaoFactory;
import com.example.tapAp2.model.PartidaSelecao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partida-selecao")
public class PartidaSelecaoController {

    private final PartidaSelecaoDao partidaSelecaoDao =
            DaoFactory.criarDao(PartidaSelecaoDao.class);

    @GetMapping
    public List<PartidaSelecao> listarTodos() {
        return partidaSelecaoDao.listarTodos();
    }

    @GetMapping("/partida/{partidaId}")
    public List<PartidaSelecao> buscarPorPartida(@PathVariable Long partidaId) {
        return partidaSelecaoDao.buscarPorPartida(partidaId);
    }

    @PostMapping
    public String inserir(@RequestBody PartidaSelecao partidaSelecao) {

        partidaSelecaoDao.inserir(partidaSelecao);

        return "Vínculo inserido com sucesso!";
    }

    @DeleteMapping("/{partidaId}/{selecaoId}")
    public String deletar(@PathVariable Long partidaId,
                          @PathVariable Long selecaoId) {

        partidaSelecaoDao.deletar(partidaId, selecaoId);

        return "Vínculo deletado com sucesso!";
    }
}