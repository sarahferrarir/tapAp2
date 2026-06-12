package com.example.tapAp2.model;

public class Selecao {

    private Long id;
    private String nomePais;
    private String tecnico;
    private Integer rankingFifa;

    public Selecao() {
    }

    public Selecao(Long id, String nomePais, String tecnico, Integer rankingFifa) {
        this.id = id;
        this.nomePais = nomePais;
        this.tecnico = tecnico;
        this.rankingFifa = rankingFifa;
    }

    public Long getId() {
        return id;
    }

    public String getNomePais() {
        return nomePais;
    }

    public String getTecnico() {
        return tecnico;
    }

    public Integer getRankingFifa() {
        return rankingFifa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public void setRankingFifa(Integer rankingFifa) {
        this.rankingFifa = rankingFifa;
    }
}