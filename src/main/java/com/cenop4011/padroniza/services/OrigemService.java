package com.cenop4011.padroniza.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.OrigemDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Origem;
import com.cenop4011.padroniza.repositories.OrigemRepository;

@Service
public class OrigemService {

	
	@Autowired
	OrigemRepository origemRepository;
	
	public Origem gravarOrigem(OrigemDTO origemDTO) {
		Origem origem = new Origem(origemDTO);
		
		return origemRepository.save(origem);
		
		
	}
	
	
	public Origem buscarOrigem(Integer idOrigem) {
		
		Optional<Origem> origem = origemRepository.findById(idOrigem);
		
		return origem.orElseThrow(()->new ObjectNotFoundException("Origem não encontrada para o id : " + idOrigem));
		
	}
	
	
}









