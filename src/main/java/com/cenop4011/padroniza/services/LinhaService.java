package com.cenop4011.padroniza.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cenop4011.padroniza.dtos.LinhaDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Linha;
import com.cenop4011.padroniza.repositories.LinhaRepository;

@Service
public class LinhaService {
	
	
	@Autowired
	LinhaRepository linhaRepository;
	
	
	public Linha gravarLinha(LinhaDTO linhaDTO) {
		
		Linha linha = new Linha();
		linha.setNomeLinha(linhaDTO.getNomeLinha());
		linha.setCodigoLinha(linhaDTO.getCodigoLinha());
		
		linha = linhaRepository.save(linha);
		return linha;
		
		
	}


	public List<Linha> buscarTodas() {
		
		return linhaRepository.findAll();
	}


	public Linha buscarLinha(Integer codigoLinha) {
		
		Optional<Linha> linha = linhaRepository.findByCodigoLinha(codigoLinha);

		return linha.orElseThrow(()->new ObjectNotFoundException("Linha com código :" + codigoLinha +  " não localizada"));
		
	}

}
