package com.cenop4011.padroniza.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Resposta;
import com.cenop4011.padroniza.repositories.RespostaRepository;

@Service
public class RespostaService {
	
	
	
	
	@Autowired
	RespostaRepository respostaRepository;
	
	public Resposta buscarResposta(Integer idResposta) {
		
		
		Optional<Resposta> resposta = respostaRepository.findById(idResposta);
		
		
		return resposta.orElseThrow(()-> new ObjectNotFoundException("Resposta não encontrada para o id :" + idResposta));
		
	}

}
