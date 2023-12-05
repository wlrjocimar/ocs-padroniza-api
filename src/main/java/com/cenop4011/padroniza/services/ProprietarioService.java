package com.cenop4011.padroniza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.ProprietarioDTO;
import com.cenop4011.padroniza.models.Proprietario;
import com.cenop4011.padroniza.repositories.ProprietarioRepository;

@Service
public class ProprietarioService {
	
	@Autowired
	ProprietarioRepository proprietarioRepository;
	
	public Proprietario gravarProprietario(ProprietarioDTO proprietarioDTO) {
		
		Proprietario proprietario = new Proprietario(proprietarioDTO);
		
		return proprietarioRepository.save(proprietario);
	}

}
