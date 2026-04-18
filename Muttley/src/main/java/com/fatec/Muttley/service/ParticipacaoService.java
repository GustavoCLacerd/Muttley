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

    @Autowired
    private MedalhaService medalhaService;

    @Transactional
    public Participacao registrarParticipacao(Participacao participacao) {
        // 1. Salva a participação no banco
        participacao.setDataParticipacao(LocalDate.now());
        if (!participacao.validarParticipacao()) {
            throw new IllegalArgumentException("Participacao invalida: aluno e evento sao obrigatorios.");
        }
        Participacao salva = participacaoRepository.save(participacao);

        // 2. Calcula os pontos com base no tipo de evento (Herança)
        Evento evento = salva.getEvento();
        Integer pontosGanhos = evento.calcularPontos();
        if (pontosGanhos == null || pontosGanhos < 0) {
            pontosGanhos = 0;
        }

        // 3. Atualiza o saldo do Aluno conforme o Tipo de Skill do evento
        Aluno aluno = salva.getAluno();
        aluno.adicionarPontos(pontosGanhos, evento.getTipoSkill());

        alunoRepository.save(aluno);
        medalhaService.verificarNovasMedalhas(aluno);

        return salva;
    }
}