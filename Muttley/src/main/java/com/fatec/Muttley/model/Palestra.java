package com.fatec.Muttley.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PALESTRA")
public class Palestra extends Evento {

    private Integer valorFixo = 5;

    public Palestra() {}

    @Override
    public Integer calcularPontos() {
        // Palestras geralmente têm pontuação fixa independente das horas
        return this.valorFixo;
    }

    // Getter e Setter
    public Integer getValorFixo() { return valorFixo; }
    public void setValorFixo(Integer valorFixo) { this.valorFixo = valorFixo; }
}