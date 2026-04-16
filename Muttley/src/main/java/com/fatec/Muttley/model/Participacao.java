package com.fatec.Muttley.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    private LocalDate dataParticipacao;
    
    // Atributo essencial para o cálculo de pontos e certificados
    private Integer horas; 
    
    private String usuarioResponsavel;

    public Participacao() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
    public LocalDate getDataParticipacao() { return dataParticipacao; }
    public void setDataParticipacao(LocalDate dataParticipacao) { this.dataParticipacao = dataParticipacao; }
    public Integer getHoras() { return horas; }
    public void setHoras(Integer horas) { this.horas = horas; }
    public String getUsuarioResponsavel() { return usuarioResponsavel; }
    public void setUsuarioResponsavel(String usuarioResponsavel) { this.usuarioResponsavel = usuarioResponsavel; }
}