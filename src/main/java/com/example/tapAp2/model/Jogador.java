package com.example.tapAp2.model;

public class Jogador {

    private Long id;
    private String nome;
    private Integer numeroCamisa;
    private String posicao;
    private Integer idade;
    private Long selecaoId;

    public Jogador() {
    }

    public Jogador(Long id,
                   String nome,
                   Integer numeroCamisa,
                   String posicao,
                   Integer idade,
                   Long selecaoId) {

        this.id = id;
        this.nome = nome;
        this.numeroCamisa = numeroCamisa;
        this.posicao = posicao;
        this.idade = idade;
        this.selecaoId = selecaoId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getNumeroCamisa() {
        return numeroCamisa;
    }

    public String getPosicao() {
        return posicao;
    }

    public Integer getIdade() {
        return idade;
    }

    public Long getSelecaoId() {
        return selecaoId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroCamisa(Integer numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setSelecaoId(Long selecaoId) {
        this.selecaoId = selecaoId;
    }
}