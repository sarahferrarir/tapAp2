package com.example.tapAp2.controller;

import com.example.tapAp2.dao.JogadorDao;
import com.example.tapAp2.model.Jogador;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    private final JogadorDao dao =
            new JogadorDao();

    @GetMapping
    public List<Jogador> listarTodos() {
        return dao.listarTodos();
    }

    @GetMapping("/{id}")
    public Jogador buscarPorId(@PathVariable Long id) {
        return dao.buscarPorId(id);
    }

    @PostMapping
    public String inserir(@RequestBody Jogador jogador) {

        dao.inserir(jogador);

        return "Jogador inserido com sucesso!";
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestBody Jogador jogador) {

        jogador.setId(id);

        dao.atualizar(jogador);

        return "Jogador atualizado com sucesso!";
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {

        dao.deletar(id);

        return "Jogador deletado com sucesso!";
    }
}