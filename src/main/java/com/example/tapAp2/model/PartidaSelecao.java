package com.example.tapAp2.model;

public class PartidaSelecao {

    private Long partidaId;
    private Long selecaoId;

    public PartidaSelecao() {
    }

    public PartidaSelecao(Long partidaId,
                          Long selecaoId) {

        this.partidaId = partidaId;
        this.selecaoId = selecaoId;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public Long getSelecaoId() {
        return selecaoId;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

    public void setSelecaoId(Long selecaoId) {
        this.selecaoId = selecaoId;
    }
}