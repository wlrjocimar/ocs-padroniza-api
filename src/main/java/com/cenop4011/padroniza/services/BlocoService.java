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
import com.cenop4011.padroniza.repositories.ChecklistRepository;

@Service
public class BlocoService {
	
	
	@Autowired
	BlocoRepository blocoRepository;
	
	@Autowired
	ChecklistService checklistService;
	
	@Autowired
	ChecklistRepository checklistRepository;
	
	
	@Transactional("padronizaTransactionManager")
	public Bloco salvarBloco(BlocoDTO blocoDto, Integer idChecklist) {
		Checklist checklist ;
		
		
		
		
		Bloco bloco = new Bloco(blocoDto);
		

		if(idChecklist>0) {
			 checklist = checklistService.buscarPorId(idChecklist);
			bloco.getChecklists().add(checklist);
			blocoRepository.save(bloco);
			checklist.getBlocos().add(bloco);
			checklistRepository.save(checklist);
		}else{
			blocoRepository.save(bloco);
			
		}
		
		return bloco;
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Transactional("padronizaTransactionManager")
	public Bloco buscarBlocoPorId(Integer idBloco) {
		
		
		 Bloco bloco = blocoRepository.findById(idBloco)
		            .orElseThrow(() -> new ObjectNotFoundException("Bloco não encontrado para o id: " + idBloco));
		    
		    bloco.getPerguntas().size(); // Isso carrega as perguntas (sem carga preguiçosa)
		    
		    return bloco;
		
	}

	
	@Transactional("padronizaTransactionManager")
	public Bloco atualizaBloco(Bloco bloco) {
		
			
		
			
				return blocoRepository.save(bloco);
			
		
	}














	@Transactional("padronizaTransactionManager")
	public Bloco vincularCheckList(Integer idBloco, Integer idCheckList) {
		Bloco bloco = buscarBlocoPorId(idBloco);
		Checklist checklist = checklistService.buscarPorId(idCheckList);
		
		bloco.getChecklists().add(checklist);
		checklist.getBlocos().add(bloco);
		bloco= blocoRepository.save(bloco);
		checklist = checklistService.atualizaChecklist(checklist);
		return bloco;
		
	}

}
