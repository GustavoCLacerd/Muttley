package com.fatec.Muttley.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_evento_discriminador")
public abstract class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @Column(length = 500)
    private String descricao; // Visto na tela de Novo Evento

    @Enumerated(EnumType.STRING)
    private TipoSkill tipoSkill;

    public Evento() {}

    // Método que será implementado pelas subclasses para definir a pontuação
    public abstract Integer calcularPontos(Integer horas);

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public TipoSkill getTipoSkill() { return tipoSkill; }
    public void setTipoSkill(TipoSkill tipoSkill) { this.tipoSkill = tipoSkill; }
}