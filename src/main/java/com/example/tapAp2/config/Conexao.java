package com.example.tapAp2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection instanciaConexao;

    private static final String URL =
            "jdbc:mysql://bne9rwzgoniv3dt9lcb8-mysql.services.clever-cloud.com:3306/bne9rwzgoniv3dt9lcb8";

    private static final String USUARIO =
            "uczcaxnj76zowtte";

    private static final String SENHA =
            "8Yaj3v6LDWBkCNl03KoN";

    private Conexao() {
        // Impede instanciação externa
    }

    public static Connection getInstanciaConexao() {

        try {

            if (instanciaConexao == null || instanciaConexao.isClosed()) {

                instanciaConexao = DriverManager.getConnection(
                        URL,
                        USUARIO,
                        SENHA
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }

        return instanciaConexao;
    }
}
