package com.fatec.Muttley.repository;

import com.fatec.Muttley.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    // Método para a busca principal por CPF
    Optional<Aluno> findByCpf(String cpf);
    
    // Método para busca por RA
    Optional<Aluno> findByRa(String ra);
}