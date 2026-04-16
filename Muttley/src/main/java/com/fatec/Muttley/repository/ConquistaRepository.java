package com.fatec.Muttley.repository;

import com.fatec.Muttley.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    
    // Lista todas as medalhas que um aluno já ganhou
    List<Conquista> findByAlunoId(Long alunoId);
}