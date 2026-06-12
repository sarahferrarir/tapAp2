package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.PartidaSelecao;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidaSelecaoDao implements CrudDao<PartidaSelecao, String> {

    @Override
    public void inserir(PartidaSelecao partidaSelecao) {

        String sql =
                "INSERT INTO partida_selecao(partida_id, selecao_id) VALUES (?, ?)";

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, partidaSelecao.getPartidaId());
            stmt.setLong(2, partidaSelecao.getSelecaoId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vínculo", e);
        }
    }

    @Override
    public PartidaSelecao buscarPorId(String id) {
        throw new UnsupportedOperationException(
                "Use buscarPorPartida(partidaId)"
        );
    }

    @Override
    public List<PartidaSelecao> listarTodos() {

        String sql =
                "SELECT * FROM partida_selecao";

        List<PartidaSelecao> lista =
                new ArrayList<>();

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                PartidaSelecao ps =
                        new PartidaSelecao(
                                rs.getLong("partida_id"),
                                rs.getLong("selecao_id")
                        );

                lista.add(ps);
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vínculos", e);
        }
    }

    @Override
    public void atualizar(PartidaSelecao partidaSelecao) {
        throw new UnsupportedOperationException(
                "Atualização não suportada para tabela de associação"
        );
    }

    @Override
    public void deletar(String id) {
        throw new UnsupportedOperationException(
                "Use deletar(partidaId, selecaoId)"
        );
    }

    public void deletar(Long partidaId, Long selecaoId) {

        String sql =
                "DELETE FROM partida_selecao WHERE partida_id = ? AND selecao_id = ?";

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, partidaId);
            stmt.setLong(2, selecaoId);

            int linhasAfetadas =
                    stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vínculo entre partida "
                                + partidaId +
                                " e seleção "
                                + selecaoId +
                                " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar vínculo", e);
        }
    }

    public List<PartidaSelecao> buscarPorPartida(Long partidaId) {

        String sql =
                "SELECT * FROM partida_selecao WHERE partida_id = ?";

        List<PartidaSelecao> lista =
                new ArrayList<>();

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, partidaId);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                PartidaSelecao ps =
                        new PartidaSelecao(
                                rs.getLong("partida_id"),
                                rs.getLong("selecao_id")
                        );

                lista.add(ps);
            }

            if (lista.isEmpty()) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nenhuma seleção encontrada para a partida "
                                + partidaId
                );
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vínculos", e);
        }
    }
}