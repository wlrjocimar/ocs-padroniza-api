package com.cenop4011.padroniza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.repositories.BlocoRepository;
import com.cenop4011.padroniza.repositories.PerguntaRepository;

@Service
public class PerguntaService {
	
	
	@Autowired
	PerguntaRepository perguntaRepository;
	
	
	@Autowired
	BlocoService blocoService;
	
	
	
	public Pergunta gravarPergunta(PerguntaDTO perguntaDTO, Integer idBloco) {
		Bloco  bloco = null;
		
		
		
		
		
		Pergunta pergunta = new Pergunta(perguntaDTO);
		
		
		if(idBloco>0) {
			  bloco = blocoService.buscarBlocoPorId(idBloco);
			  pergunta.adicionarBlocos(bloco);
		}
		
		
		try {
			perguntaRepository.save(pergunta);
			blocoService.atualizaBloco(bloco);
		} catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			throw e;
		}
		
		return pergunta;
		
	}



	public List<Pergunta> buscarTodas() {
		return perguntaRepository.findAll();
	}
	
	

}
