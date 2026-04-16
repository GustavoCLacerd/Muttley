package com.fatec.Muttley.service;

import com.fatec.Muttley.model.*;
import com.fatec.Muttley.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ParticipacaoService {

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public Participacao registrarParticipacao(Participacao participacao) {
        // 1. Salva a participação no banco
        participacao.setDataParticipacao(LocalDate.now());
        Participacao salva = participacaoRepository.save(participacao);

        // 2. Calcula os pontos com base no tipo de evento (Herança)
        Evento evento = salva.getEvento();
        Integer pontosGanhos = evento.calcularPontos(salva.getHoras());

        // 3. Atualiza o saldo do Aluno conforme o Tipo de Skill do evento
        Aluno aluno = salva.getAluno();
        if (evento.getTipoSkill() == TipoSkill.HARD_SKILL) {
            aluno.setSaldoHardSkill(aluno.getSaldoHardSkill() + pontosGanhos);
        } else {
            aluno.setSaldoSoftSkill(aluno.getSaldoSoftSkill() + pontosGanhos);
        }

        alunoRepository.save(aluno);
        
        // Aqui poderíamos chamar a verificação de medalhas futuramente
        return salva;
    }
}