package com.cenop4011.padroniza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.models.PerguntaHistorico;
import com.cenop4011.padroniza.repositories.PerguntaHistoricoRepository;

@Service
public class PerguntaHistoricoService {
	
	
	
	
	@Autowired
	PerguntaHistoricoRepository perguntaHistoricoRepository;
	
	
	
	public PerguntaHistorico gravarPerguntaHistorico(PerguntaHistorico perguntaHistorico) {
		perguntaHistorico.setId(null);
		
		PerguntaHistorico perguntaHistoricoGravada= perguntaHistoricoRepository.save(perguntaHistorico);
		
		
		
		return perguntaHistoricoGravada;
	}

}
