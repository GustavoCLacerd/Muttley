package com.fatec.Muttley.repository;

import com.fatec.Muttley.model.Medalha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedalhaRepository extends JpaRepository<Medalha, Long> {
}
