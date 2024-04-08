package com.cenop4011.padroniza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.ValorRespostaDTO;
import com.cenop4011.padroniza.models.ValorResposta;
import com.cenop4011.padroniza.repositories.ValorRespostaRepository;

@Service
public class ValorRespostaService {
	
	
	@Autowired
	ValorRespostaRepository valorRespostaRepository;
	
	
	public ValorResposta  gravarValorResposta(ValorRespostaDTO valorRespostaDTO){
		
		ValorResposta valorResposta = new ValorResposta();
		valorResposta.setAtiva(valorRespostaDTO.getAtiva());
		valorResposta.setDescricao(valorRespostaDTO.getDescricao());
		
		ValorResposta valorRespostaGravada =valorRespostaRepository.save(valorResposta);
		return valorRespostaGravada;
		
	}


	public List<ValorResposta> buscarTodos() {
		
		List<ValorResposta> valoresRespostas = valorRespostaRepository.findAll();
		
		return valoresRespostas;
	}
	
	
	

}
