package com.example.tapAp2.dao;

import com.example.tapAp2.config.Conexao;
import com.example.tapAp2.model.ProdutoFornecedor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFornecedorDao implements CrudDao<ProdutoFornecedor, String> {

    @Override
    public void inserir(ProdutoFornecedor produtoFornecedor) {

        String sql =
                "INSERT INTO produto_fornecedor(produto_id, fornecedor_id) VALUES (?, ?)";

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, produtoFornecedor.getProdutoId());
            stmt.setLong(2, produtoFornecedor.getFornecedorId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir vínculo", e);
        }
    }

    @Override
    public ProdutoFornecedor buscarPorId(String id) {
        throw new UnsupportedOperationException(
                "Use buscarPorProduto(produtoId)"
        );
    }

    @Override
    public List<ProdutoFornecedor> listarTodos() {

        String sql =
                "SELECT * FROM produto_fornecedor";

        List<ProdutoFornecedor> lista =
                new ArrayList<>();

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                ProdutoFornecedor pf =
                        new ProdutoFornecedor(
                                rs.getLong("produto_id"),
                                rs.getLong("fornecedor_id")
                        );

                lista.add(pf);
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vínculos", e);
        }
    }

    @Override
    public void atualizar(ProdutoFornecedor produtoFornecedor) {
        throw new UnsupportedOperationException(
                "Atualização não suportada para tabela de associação"
        );
    }

    @Override
    public void deletar(String id) {
        throw new UnsupportedOperationException(
                "Use deletar(produtoId, fornecedorId)"
        );
    }

    public void deletar(Long produtoId, Long fornecedorId) {

        String sql =
                "DELETE FROM produto_fornecedor WHERE produto_id = ? AND fornecedor_id = ?";

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, produtoId);
            stmt.setLong(2, fornecedorId);

            int linhasAfetadas =
                    stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vínculo entre produto "
                                + produtoId +
                                " e fornecedor "
                                + fornecedorId +
                                " não encontrado."
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar vínculo", e);
        }
    }

    public List<ProdutoFornecedor> buscarPorProduto(Long produtoId) {

        String sql =
                "SELECT * FROM produto_fornecedor WHERE produto_id = ?";

        List<ProdutoFornecedor> lista =
                new ArrayList<>();

        try {

            Connection conexao =
                    Conexao.getInstanciaConexao();

            PreparedStatement stmt =
                    conexao.prepareStatement(sql);

            stmt.setLong(1, produtoId);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                ProdutoFornecedor pf =
                        new ProdutoFornecedor(
                                rs.getLong("produto_id"),
                                rs.getLong("fornecedor_id")
                        );

                lista.add(pf);
            }

            if (lista.isEmpty()) {

                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nenhum fornecedor encontrado para o produto "
                                + produtoId
                );
            }

            return lista;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar vínculos", e);
        }
    }
}