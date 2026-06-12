package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.Selecao;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelecaoDao implements CrudDao<Selecao, Long> {

    @Override
    public void inserir(Selecao selecao) {
        String sql = "INSERT INTO selecoes(nome_pais, tecnico, ranking_fifa) VALUES (?, ?, ?)";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, selecao.getNomePais());
            stmt.setString(2, selecao.getTecnico());
            stmt.setInt(3, selecao.getRankingFifa());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir seleção", e);
        }
    }

    @Override
    public Selecao buscarPorId(Long id) {
        String sql = "SELECT * FROM selecoes WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Selecao(
                        rs.getLong("id"),
                        rs.getString("nome_pais"),
                        rs.getString("tecnico"),
                        rs.getInt("ranking_fifa")
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Seleção com ID " + id + " não encontrada."
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar seleção por ID", e);
        }
    }

    @Override
    public List<Selecao> listarTodos() {
        String sql = "SELECT * FROM selecoes";

        List<Selecao> selecoes = new ArrayList<>();

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Selecao selecao = new Selecao(
                        rs.getLong("id"),
                        rs.getString("nome_pais"),
                        rs.getString("tecnico"),
                        rs.getInt("ranking_fifa")
                );

                selecoes.add(selecao);
            }

            return selecoes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar seleções", e);
        }
    }

    @Override
    public void atualizar(Selecao selecao) {
        String sql = "UPDATE selecoes SET nome_pais = ?, tecnico = ?, ranking_fifa = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, selecao.getNomePais());
            stmt.setString(2, selecao.getTecnico());
            stmt.setInt(3, selecao.getRankingFifa());
            stmt.setLong(4, selecao.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Seleção com ID " + selecao.getId() + " não encontrada."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar seleção", e);
        }
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM selecoes WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Seleção com ID " + id + " não encontrada."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar seleção", e);
        }
    }
}