package com.cenop4011.padroniza.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.BlocoDTO;
import com.cenop4011.padroniza.dtos.PosicaoBlocoInputDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.ViolacaoIntegridadeException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.models.PosicaoBloco;
import com.cenop4011.padroniza.models.PosicaoBlocoId;
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
		    
		    for (Pergunta pergunta : bloco.getPerguntas()) {
				pergunta.getListaCodigosLinha().size();
			}
		    
		    
		    return bloco;
		
	}

	
	@Transactional("padronizaTransactionManager")
	public Bloco atualizaBloco(Bloco bloco) {
		
			
		
			
				return blocoRepository.save(bloco);
			
		
	}














	@Transactional("padronizaTransactionManager")
	public Bloco vincularCheckList(Integer idBloco, Integer idCheckList, Integer posicao) {
		try {
			Bloco bloco = buscarBlocoPorId(idBloco);
			
			Checklist checklist = checklistService.buscarPorId(idCheckList);
			PosicaoBloco posicaoBloco = new PosicaoBloco();
			posicaoBloco.setPosicao(posicao);;
			posicaoBloco.setBloco(bloco);
			posicaoBloco.setChecklist(checklist);
			
			
			bloco.getChecklists().add(checklist);
			bloco.getPosicaoBlocos().add(posicaoBloco);
			checklist.getBlocos().add(bloco);
			
			bloco = blocoRepository.save(bloco);
			checklist = checklistService.atualizaChecklist(checklist);
			
			
			
			return bloco;
			
		} catch (StackOverflowError e) {
			
			throw new ViolacaoIntegridadeException("violação de integridade de chave composta : " + e.toString());
		}
		
	}














	@Transactional("padronizaTransactionManager")
	public List<Bloco> buscarTodos() {
		
		List<Bloco> blocos = blocoRepository.findAll();
	    
	    for (Bloco bloco : blocos) {
	        // Carrega a lista de perguntas dentro do contexto da transação ativa
	        bloco.getPerguntas().size();
	    }
	    
	    return blocos;
	}















	public void deletarBloco(Integer idBloco) {
		Bloco bloco = buscarBlocoPorId(idBloco);
		blocoRepository.delete(bloco);
		
	}














	@Transactional("padronizaTransactionManager")
	public void removerBloco(Integer idBloco, Integer idChecklist) {
		 Bloco bloco = buscarBlocoPorId(idBloco);
		    Checklist checklist  =null;
		    PosicaoBlocoId posicaoBlocoId=null;
		    
		    
		    
		    if (idChecklist > 0) {
		    	checklist = checklistService.buscarPorId(idChecklist);
		    	posicaoBlocoId = new PosicaoBlocoId(checklist, bloco);
		    }
		    
		   
		    
		    
		    
		    if(bloco.getChecklists().contains(checklist)) {
		    	
		    	
		    	 if(bloco.getPosicaoBlocos().size()>0) {
		    		 Checklist checklistInteresse = checklistService.buscarPorId(idChecklist);
		 	    	 PosicaoBloco posicaoBlocoDesejada = bloco.getPosicaoBlocos().stream()
		 	    		    .filter(posicao -> posicao.getChecklist().equals(checklistInteresse))
		 	    		    .findFirst()
		 	    		    .orElse(null);
		 	    	
		 	    	bloco.getPosicaoBlocos().remove(posicaoBlocoDesejada);
		 				
		 			}
		    	
		    	
		    	checklist.getBlocos().remove(bloco);
		        bloco.getChecklists().remove(checklist);
		    } else if(bloco.getChecklists().size()>0) {
		    	
		    	throw new  ObjectNotFoundException("Checklist " + idChecklist + " não vinculado ao bloco");
		    }
		    
		   
		    
		    try {
		        blocoRepository.save(bloco);
		    } catch (DataIntegrityViolationException ex) {
		        throw new ViolacaoIntegridadeException("Não é possível excluir o bloco porque está sendo usada em outro lugar", ex);
		    }
		
	}














	@Transactional("padronizaTransactionManager")
	public void atualizarTodasPosicoes(List<PosicaoBlocoInputDTO> posicoesBlocos, Integer idChecklist) {
		
		

		Checklist checklist = checklistService.buscarPorId(idChecklist);
		checklist.getBlocos();
		
		
		
		
		
		List<Bloco> copiaListaBlocosChecklist = new ArrayList<>(checklist.getBlocos());

		for (Bloco bloco : copiaListaBlocosChecklist) {
		    removerBloco(bloco.getId(), idChecklist);
		}
		
		
		
		
		

			
		for (PosicaoBlocoInputDTO posicaoBlocoInputDTO : posicoesBlocos) {
					
					
					
					
					Bloco bloco = buscarBlocoPorId(posicaoBlocoInputDTO.getIdBloco());
					
					//pergunta.getBlocos();
					
					vincularCheckList(posicaoBlocoInputDTO.getIdBloco(), idChecklist, posicaoBlocoInputDTO.getPosicao());
					
					
					
					
				}
		
		
		
		
	}

	
	
	
	
}
