package com.fatec.Muttley.model;

import jakarta.persistence.*;

@Entity
public class Medalha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 500)
    private String descricao;

    private Integer pontosNecessarios;

    @Enumerated(EnumType.STRING)
    private TipoSkill tipoRequisito;

    private String urlIcone;

    public Medalha() {}

    public boolean verificarElegibilidade(Aluno aluno) {
        if (aluno == null || tipoRequisito == null || pontosNecessarios == null) {
            return false;
        }

        if (tipoRequisito == TipoSkill.HARD_SKILL) {
            return aluno.getSaldoHardSkill() != null && aluno.getSaldoHardSkill() >= pontosNecessarios;
        }

        return aluno.getSaldoSoftSkill() != null && aluno.getSaldoSoftSkill() >= pontosNecessarios;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getPontosNecessarios() { return pontosNecessarios; }
    public void setPontosNecessarios(Integer pontosNecessarios) { this.pontosNecessarios = pontosNecessarios; }
    public TipoSkill getTipoRequisito() { return tipoRequisito; }
    public void setTipoRequisito(TipoSkill tipoRequisito) { this.tipoRequisito = tipoRequisito; }
    public String getUrlIcone() { return urlIcone; }
    public void setUrlIcone(String urlIcone) { this.urlIcone = urlIcone; }
}
