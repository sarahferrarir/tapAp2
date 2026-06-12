package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao implements CrudDao<Categoria, Long> {

    @Override
    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categorias(nome, descricao) VALUES (?, ?)";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir categoria", e);
        }
    }

    @Override
    public Categoria buscarPorId(Long id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Categoria(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Categoria com ID " + id + " não encontrada."
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar categoria por ID", e);
        }
    }

    @Override
    public List<Categoria> listarTodos() {
        String sql = "SELECT * FROM categorias";

        List<Categoria> categorias = new ArrayList<>();

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("descricao")
                );

                categorias.add(categoria);
            }

            return categorias;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar categorias", e);
        }
    }

    @Override
    public void atualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nome = ?, descricao = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setLong(3, categoria.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Categoria com ID " + categoria.getId() + " não encontrada."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar categoria", e);
        }
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM categorias WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Categoria com ID " + id + " não encontrada."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar categoria", e);
        }
    }
}