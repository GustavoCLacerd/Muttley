package com.fatec.Muttley.model;

import jakarta.persistence.*;

@Entity
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String cpf; // Campo principal de busca nas telas
    
    private String ra;
    private String nome;
    private String curso;
    private String email;
    
    private Integer saldoHardSkill = 0;
    private Integer saldoSoftSkill = 0;

    // Construtor padrão exigido pelo JPA
    public Aluno() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getRa() { return ra; }
    public void setRa(String ra) { this.ra = ra; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Integer getSaldoHardSkill() { return saldoHardSkill; }
    public void setSaldoHardSkill(Integer saldoHardSkill) { this.saldoHardSkill = saldoHardSkill; }
    public Integer getSaldoSoftSkill() { return saldoSoftSkill; }
    public void setSaldoSoftSkill(Integer saldoSoftSkill) { this.saldoSoftSkill = saldoSoftSkill; }
}