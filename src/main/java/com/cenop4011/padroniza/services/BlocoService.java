package com.cenop4011.padroniza.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.BlocoDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.repositories.BlocoRepository;

@Service
public class BlocoService {
	
	
	@Autowired
	BlocoRepository blocoRepository;
	
	public Bloco salvarBloco(BlocoDTO blocoDto) {
		
		Bloco bloco = new Bloco();
		bloco.setId(null);
		bloco.setNomeBloco(blocoDto.getNomeBloco());
		return blocoRepository.save(bloco);
		
		
	}

	public Bloco buscarBlocoPorId(Integer idBloco) {
		
		
		Optional<Bloco> bloco = blocoRepository.findById(idBloco);
		
		return bloco.orElseThrow(()->new ObjectNotFoundException("Bloco não encontrado para o id: " + idBloco));
		
	}

}
