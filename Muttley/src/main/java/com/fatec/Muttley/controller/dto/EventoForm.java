package com.fatec.Muttley.controller.dto;

/**
 * DTO simples usado apenas para abrir o formulario da tela "Novo Evento".
 * A persistencia de eventos ainda nao esta implementada (o model Evento e abstrato).
 */
public class EventoForm {

    private String tipoEvento;
    private String descricao;

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
