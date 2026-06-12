package com.example.tapAp2.model;

public class ProdutoFornecedor {

    private Long produtoId;
    private Long fornecedorId;

    public ProdutoFornecedor() {
    }

    public ProdutoFornecedor(Long produtoId,
                             Long fornecedorId) {

        this.produtoId = produtoId;
        this.fornecedorId = fornecedorId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }
}