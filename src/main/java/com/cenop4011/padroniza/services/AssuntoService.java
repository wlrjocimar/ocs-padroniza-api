package com.cenop4011.padroniza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.AssuntoDTO;
import com.cenop4011.padroniza.models.Assunto;
import com.cenop4011.padroniza.repositories.AssuntoRepository;

@Service
public class AssuntoService {

	@Autowired
	AssuntoRepository assuntoRepository;
	
	public Assunto gravarAssunto(AssuntoDTO assuntoDTO) {
		
		Assunto assunto = new Assunto(assuntoDTO);
		return	assuntoRepository.save(assunto);
		
		
		
		
	}

	
	
}
