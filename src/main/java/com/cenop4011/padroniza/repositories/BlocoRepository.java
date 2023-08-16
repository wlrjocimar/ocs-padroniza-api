package com.cenop4011.padroniza.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cenop4011.padroniza.models.Bloco;

public interface BlocoRepository extends JpaRepository<Bloco, Integer> {
	
	@Query("SELECT b FROM Bloco b JOIN FETCH b.perguntas WHERE b.id = :blocoId")
    Bloco findBlocoWithPerguntas(@Param("blocoId") Integer blocoId);

}
