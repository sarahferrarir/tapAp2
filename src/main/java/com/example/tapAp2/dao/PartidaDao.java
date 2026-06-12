package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.Partida;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidaDao implements CrudDao<Partida, Long> {

    @Override
    public void inserir(Partida partida) {

        String sql =
                "INSERT INTO partidas(data_partida, estadio, fase, placar) VALUES (?, ?, ?, ?)";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, partida.getDataPartida());
            stmt.setString(2, partida.getEstadio());
            stmt.setString(3, partida.getFase());
            stmt.setString(4, partida.getPlacar());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir partida", e);
        }
    }

    @Override
    public Partida buscarPorId(Long id) {

        String sql =
                "SELECT * FROM partidas WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Partida(
                        rs.getLong("id"),
                        rs.getString("data_partida"),
                        rs.getString("estadio"),
                        rs.getString("fase"),
                        rs.getString("placar")
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Partida com ID " + id + " não encontrada."
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar partida", e);
        }
    }

    @Override
    public List<Partida> listarTodos() {

        String sql =
                "SELECT * FROM partidas";

        List<Partida> partidas =
                new ArrayList<>();

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Partida partida =
                        new Partida(
                                rs.getLong("id"),
                                rs.getString("data_partida"),
                                rs.getString("estadio"),
                                rs.getString("fase"),
                                rs.getString("placar")
                        );

                partidas.add(partida);
            }

            return partidas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar partidas", e);
        }
    }

    @Override
    public void atualizar(Partida partida) {

        String sql =
                "UPDATE partidas SET data_partida = ?, estadio = ?, fase = ?, placar = ? WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, partida.getDataPartida());
            stmt.setString(2, partida.getEstadio());
            stmt.setString(3, partida.getFase());
            stmt.setString(4, partida.getPlacar());
            stmt.setLong(5, partida.getId());

            int linhasAfetadas =
                    stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Partida com ID " + partida.getId() + " não encontrada."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar partida", e);
        }
    }

    @Override
    public void deletar(Long id) {

        String sql =
                "DELETE FROM partidas WHERE id = ?";

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
                        "Partida com ID " + id + " não encontrada."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar partida", e);
        }
    }
}