package com.cenop4011.padroniza.services;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.BlocoDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.repositories.BlocoRepository;

@Service
public class BlocoService {
	
	
	@Autowired
	BlocoRepository blocoRepository;
	
	@Autowired
	ChecklistService checklistService;
	
	public Bloco salvarBloco(BlocoDTO blocoDto, Integer idChecklist) {
		Checklist checklist ;
		
		
		
		
		Bloco bloco = new Bloco();
		

		if(idChecklist>0) {
			 checklist = checklistService.buscarPorId(idChecklist);
			// bloco.setChecklist(checklist);
		}
		
		bloco.setId(null);
		bloco.setNomeBloco(blocoDto.getNomeBloco());
		return blocoRepository.save(bloco);
		
		
	}

	public Bloco buscarBlocoPorId(Integer idBloco) {
		
		
		Optional<Bloco> bloco = blocoRepository.findById(idBloco);
		
		return bloco.orElseThrow(()->new ObjectNotFoundException("Bloco não encontrado para o id: " + idBloco));
		
	}

	
	
	public Bloco atualizaBloco(Bloco bloco) {
		
			
		
			try {
				bloco = blocoRepository.save(bloco);
				return bloco;
				
			} catch (Exception e) {
				System.out.println("Erro ao atualizar o bloco" + e.getCause().getMessage());
				return null;
			}
		
	}

}
