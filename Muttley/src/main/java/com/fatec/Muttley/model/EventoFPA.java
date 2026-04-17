package com.fatec.Muttley.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FPA")
public class EventoFPA extends Evento {

    private Integer horasTrabalhadas;
    private Double multiplicadorCargo;

    public EventoFPA() {}

    @Override
    public Integer calcularPontos() {
        // Cálculo baseado nas horas trabalhadas no Fatec Portas Abertas
        if (horasTrabalhadas == null || multiplicadorCargo == null) {
            return 0;
        }
        return (int) (horasTrabalhadas * multiplicadorCargo);
    }

    // Getter e Setter
    public Integer getHorasTrabalhadas() { return horasTrabalhadas; }
    public void setHorasTrabalhadas(Integer horasTrabalhadas) { this.horasTrabalhadas = horasTrabalhadas; }
    public Double getMultiplicadorCargo() { return multiplicadorCargo; }
    public void setMultiplicadorCargo(Double multiplicadorCargo) { this.multiplicadorCargo = multiplicadorCargo; }
}