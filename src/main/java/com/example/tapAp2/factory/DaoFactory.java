package com.example.tapAp2.factory;

public class DaoFactory {

    public static <T> T criarDao(Class<T> classeDao) {

        try {

            return classeDao
                    .getDeclaredConstructor()
                    .newInstance();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao criar DAO",
                    e
            );

        }
    }
}