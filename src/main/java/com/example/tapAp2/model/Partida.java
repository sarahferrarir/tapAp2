package com.example.tapAp2.model;

public class Partida {

    private Long id;
    private String dataPartida;
    private String estadio;
    private String fase;
    private String placar;

    public Partida() {
    }

    public Partida(Long id,
                   String dataPartida,
                   String estadio,
                   String fase,
                   String placar) {

        this.id = id;
        this.dataPartida = dataPartida;
        this.estadio = estadio;
        this.fase = fase;
        this.placar = placar;
    }

    public Long getId() {
        return id;
    }

    public String getDataPartida() {
        return dataPartida;
    }

    public String getEstadio() {
        return estadio;
    }

    public String getFase() {
        return fase;
    }

    public String getPlacar() {
        return placar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataPartida(String dataPartida) {
        this.dataPartida = dataPartida;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }
}