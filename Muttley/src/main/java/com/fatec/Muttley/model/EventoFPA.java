package com.fatec.Muttley.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FPA")
public class EventoFPA extends Evento {

    private Double multiplicadorCargo;

    public EventoFPA() {}

    @Override
    public Integer calcularPontos(Integer horas) {
        // Cálculo baseado nas horas trabalhadas no Fatec Portas Abertas
        if (horas == null || multiplicadorCargo == null) return 0;
        return (int) (horas * multiplicadorCargo);
    }

    // Getter e Setter
    public Double getMultiplicadorCargo() { return multiplicadorCargo; }
    public void setMultiplicadorCargo(Double multiplicadorCargo) { this.multiplicadorCargo = multiplicadorCargo; }
}