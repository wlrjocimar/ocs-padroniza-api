package com.cenop4011.padroniza.services;

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
		
		Bloco  bloco = blocoService.buscarBlocoPorId(idBloco);
		
		Pergunta pergunta = new Pergunta();
		pergunta.setId(null);
		pergunta.setDescricao(perguntaDTO.getDescricao());
		pergunta.setAjuda(perguntaDTO.getAjuda());
		pergunta.setInstrucaoIn(perguntaDTO.getInstrucaoIn());
		pergunta.setTipo(perguntaDTO.getTipo());
		pergunta.setBloco(bloco);
		
		try {
			perguntaRepository.save(pergunta);
		} catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			throw e;
		}
		
		return pergunta;
		
	}
	
	

}
