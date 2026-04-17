package com.fatec.Muttley.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Conquista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "medalha_id", nullable = false)
    private Medalha medalha;

    private LocalDate dataConquista;
    
    private String urlCompartilhamento;
    
    private Boolean postLinkedIn = false; // Visto no seu diagrama técnico

    public Conquista() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Medalha getMedalha() { return medalha; }
    public void setMedalha(Medalha medalha) { this.medalha = medalha; }
    public LocalDate getDataConquista() { return dataConquista; }
    public void setDataConquista(LocalDate dataConquista) { this.dataConquista = dataConquista; }
    public String getUrlCompartilhamento() { return urlCompartilhamento; }
    public void setUrlCompartilhamento(String urlCompartilhamento) { this.urlCompartilhamento = urlCompartilhamento; }
    public Boolean getPostLinkedIn() { return postLinkedIn; }
    public void setPostLinkedIn(Boolean postLinkedIn) { this.postLinkedIn = postLinkedIn; }

    public String gerarLinkCompartilhamento() {
        if (urlCompartilhamento == null || urlCompartilhamento.isBlank()) {
            String medalhaId = medalha != null && medalha.getId() != null ? medalha.getId().toString() : "sem-medalha";
            String alunoId = aluno != null && aluno.getId() != null ? aluno.getId().toString() : "sem-aluno";
            urlCompartilhamento = "https://muttley.local/conquista/" + alunoId + "/" + medalhaId;
        }
        return urlCompartilhamento;
    }

    public void enviarNotificacao() {
        this.postLinkedIn = Boolean.TRUE;
    }
}