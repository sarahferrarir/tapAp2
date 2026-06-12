package com.example.tapAp2.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    void inserir(T objeto);

    T buscarPorId(ID id);

    List<T> listarTodos();

    void atualizar(T objeto);

    void deletar(ID id);
}