package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao implements CrudDao<Produto, Long> {

    @Override
    public void inserir(Produto produto) {

        String sql =
                "INSERT INTO produtos(nome, preco, categoria_id) VALUES (?, ?, ?)";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setLong(3, produto.getCategoriaId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir produto", e);
        }
    }

    @Override
    public Produto buscarPorId(Long id) {

        String sql =
                "SELECT * FROM produtos WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new Produto(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getLong("categoria_id")
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Produto com ID " + id + " não encontrado."
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto", e);
        }
    }

    @Override
    public List<Produto> listarTodos() {

        String sql =
                "SELECT * FROM produtos";

        List<Produto> produtos =
                new ArrayList<>();

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto =
                        new Produto(
                                rs.getLong("id"),
                                rs.getString("nome"),
                                rs.getDouble("preco"),
                                rs.getLong("categoria_id")
                        );

                produtos.add(produto);
            }

            return produtos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
    }

    @Override
    public void atualizar(Produto produto) {

        String sql =
                "UPDATE produtos SET nome = ?, preco = ?, categoria_id = ? WHERE id = ?";

        try {

            Connection conexao = Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setLong(3, produto.getCategoriaId());
            stmt.setLong(4, produto.getId());

            int linhasAfetadas =
                    stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto com ID " + produto.getId() + " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

    @Override
    public void deletar(Long id) {

        String sql =
                "DELETE FROM produtos WHERE id = ?";

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
                        "Produto com ID " + id + " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto", e);
        }
    }
}