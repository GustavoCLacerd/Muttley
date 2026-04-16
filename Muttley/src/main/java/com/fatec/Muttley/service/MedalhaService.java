package com.fatec.Muttley.service;

import com.fatec.Muttley.model.*;
import com.fatec.Muttley.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MedalhaService {

    @Autowired
    private MedalhaRepository medalhaRepository;

    @Autowired
    private ConquistaRepository conquistaRepository;

    public void verificarNovasMedalhas(Aluno aluno) {
        List<Medalha> todasMedalhas = medalhaRepository.findAll();
        
        for (Medalha m : todasMedalhas) {
            // Lógica simples: se o saldo atingiu o requisito e ele ainda não tem a medalha
            boolean jaPossui = conquistaRepository.findByAlunoId(aluno.getId())
                               .stream().anyMatch(c -> c.getMedalha().equals(m));

            if (!jaPossui) {
                if ((m.getTipoRequisito() == TipoSkill.HARD_SKILL && aluno.getSaldoHardSkill() >= m.getPontosNecessarios()) ||
                    (m.getTipoRequisito() == TipoSkill.SOFT_SKILL && aluno.getSaldoSoftSkill() >= m.getPontosNecessarios())) {
                    
                    Conquista nova = new Conquista();
                    nova.setAluno(aluno);
                    nova.setMedalha(m);
                    nova.setDataConquista(LocalDate.now());
                    conquistaRepository.save(nova);
                }
            }
        }
    }
}