package com.fatec.Muttley.repository;

import com.fatec.Muttley.model.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Long> {
    
    // Busca todas as participações de um aluno específico para gerar o histórico
    List<Participacao> findByAlunoId(Long alunoId);
    
    // Busca participações por CPF do aluno (útil para as telas de busca)
    List<Participacao> findByAlunoCpf(String cpf);
}