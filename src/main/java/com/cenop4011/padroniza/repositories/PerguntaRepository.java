package com.cenop4011.padroniza.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cenop4011.padroniza.models.Pergunta;



public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {

	List<Pergunta> findByAutomatizavel(boolean b);
	
	

}
