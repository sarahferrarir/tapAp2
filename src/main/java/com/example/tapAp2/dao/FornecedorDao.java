package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.Fornecedor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDao implements CrudDao<Fornecedor, Long> {

    @Override
    public void inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores(nome, email) VALUES (?, ?)";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEmail());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir fornecedor", e);
        }
    }

    @Override
    public Fornecedor buscarPorId(Long id) {
        String sql = "SELECT * FROM fornecedores WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fornecedor(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                );
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Fornecedor com ID " + id + " não encontrado."
            );

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar fornecedor por ID", e);
        }
    }

    @Override
    public List<Fornecedor> listarTodos() {
        String sql = "SELECT * FROM fornecedores";

        List<Fornecedor> fornecedores = new ArrayList<>();

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                );

                fornecedores.add(fornecedor);
            }

            return fornecedores;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar fornecedores", e);
        }
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedores SET nome = ?, email = ? WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getEmail());
            stmt.setLong(3, fornecedor.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fornecedor com ID " + fornecedor.getId() + " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fornecedor", e);
        }
    }

    @Override
    public void deletar(Long id) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";

        try {
            Connection conexao = Conexao.getInstanciaConexao();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fornecedor com ID " + id + " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fornecedor", e);
        }
    }
}