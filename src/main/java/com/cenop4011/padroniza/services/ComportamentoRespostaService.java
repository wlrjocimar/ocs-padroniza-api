package com.cenop4011.padroniza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.models.ComportamentoResposta;
import com.cenop4011.padroniza.models.Resposta;
import com.cenop4011.padroniza.repositories.ComportamentoRespostaRepository;


@Service
public class ComportamentoRespostaService {

	
	
   @Autowired
   ComportamentoRespostaRepository comportamentoRespostaRepository;
	
	
	@Autowired
	RespostaService respostaService;
	
	public ComportamentoResposta gravarValorComportamentoResposta(ComportamentoRespostaDTO comportamentoRespostaDTO, Integer idResposta) {
		
		Resposta resposta = respostaService.buscarResposta(idResposta);
		
		 ComportamentoResposta comportamentoResposta = new ComportamentoResposta(comportamentoRespostaDTO);
		
		 comportamentoResposta.setResposta(resposta);
		
		 
		
		return comportamentoRespostaRepository.save(comportamentoResposta);
		
	
		
		
		
		
		
		
		
	}
	
	
}
