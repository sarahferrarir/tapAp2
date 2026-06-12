package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.Jogador;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao implements CrudDao<Jogador, Long> {

    @Override
    public void inserir(Jogador jogador) {

        String sql =
                "INSERT INTO jogadores(nome, numero_camisa, posicao, idade, selecao_id) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, jogador.getNome());
            stmt.setInt(2, jogador.getNumeroCamisa());
            stmt.setString(3, jogador.getPosicao());
            stmt.setInt(4, jogador.getIdade());
            stmt.setLong(5, jogador.getSelecaoId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir jogador", e);
        }
    }

    @Override
    public Jogador buscarPorId(Long id) {

        String sql =
                "SELECT * FROM jogadores WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Jogador(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getInt("numero_camisa"),
                        rs.getString("posicao"),
                        rs.getInt("idade"),
                        rs.getLong("selecao_id")
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Jogador com ID " + id + " não encontrado."
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar jogador", e);
        }
    }

    @Override
    public List<Jogador> listarTodos() {

        String sql =
                "SELECT * FROM jogadores";

        List<Jogador> jogadores =
                new ArrayList<>();

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Jogador jogador =
                        new Jogador(
                                rs.getLong("id"),
                                rs.getString("nome"),
                                rs.getInt("numero_camisa"),
                                rs.getString("posicao"),
                                rs.getInt("idade"),
                                rs.getLong("selecao_id")
                        );

                jogadores.add(jogador);
            }

            return jogadores;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar jogadores", e);
        }
    }

    @Override
    public void atualizar(Jogador jogador) {

        String sql =
                "UPDATE jogadores SET nome = ?, numero_camisa = ?, posicao = ?, idade = ?, selecao_id = ? WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, jogador.getNome());
            stmt.setInt(2, jogador.getNumeroCamisa());
            stmt.setString(3, jogador.getPosicao());
            stmt.setInt(4, jogador.getIdade());
            stmt.setLong(5, jogador.getSelecaoId());
            stmt.setLong(6, jogador.getId());

            int linhasAfetadas =
                    stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Jogador com ID " + jogador.getId() + " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar jogador", e);
        }
    }

    @Override
    public void deletar(Long id) {

        String sql =
                "DELETE FROM jogadores WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            int linhasAfetadas =
                    stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Jogador com ID " + id + " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar jogador", e);
        }
    }
}