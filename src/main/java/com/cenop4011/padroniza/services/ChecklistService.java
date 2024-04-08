package com.cenop4011.padroniza.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.ChecklistDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.models.ComportamentoResposta;
import com.cenop4011.padroniza.models.Linha;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.models.Resposta;
import com.cenop4011.padroniza.repositories.ChecklistRepository;


@Service
public class ChecklistService {
	
	@Autowired
	ChecklistRepository checklistRepository;
	
	
	@Autowired
	LinhaService linhaService;
	
	@Transactional("padronizaTransactionManager")
	public Checklist gravarChecklist(ChecklistDTO checklistDTO, Integer codigoLinha) {
		
		Linha linha ;
		
		Checklist checklist = new Checklist(checklistDTO);
		
		
		
		if(codigoLinha>0) {
			linha = linhaService.buscarLinha(codigoLinha);
			checklist.setLinha(linha);
			checklistRepository.save(checklist);
			
		}else {
			checklistRepository.save(checklist);
		}
		
		
		
		return checklist;
		
	}


	
	@Transactional("padronizaTransactionManager")
	public List<Checklist> buscarTodos() {
		
		List<Checklist> checklists = checklistRepository.findAll();
		
		for (Checklist checklist : checklists) { /// tecnica de carregamento quando o relacionamento tem carregamento lazy , que é o ideal
			checklist.getBlocos().size();
			
			for (Bloco bloco : checklist.getBlocos()) {
				
				bloco.getPerguntas().size();
			}
			
		}
		return checklists;
		
	}


	@Transactional("padronizaTransactionManager")
	public Checklist buscarPorId(Integer idChecklist) {
		
		

		 Checklist checklist = checklistRepository.findById(idChecklist)
		            .orElseThrow(() -> new ObjectNotFoundException("Checklist não encontrado para o id: " + idChecklist));
		    
		 checklist.getBlocos().size(); // Isso carrega as perguntas (sem carga preguiçosa)
		 
		 for (Bloco bloco :  checklist.getBlocos()) {
			bloco.getPerguntas().size();
			
			for (Pergunta perguntaParaAnalise : bloco.getPerguntas()) {
				
				
					for (Resposta respostaParaAnalise : perguntaParaAnalise.getRespostas()) {
											
											List<ComportamentoResposta> comportamentosFiltrados = new ArrayList<>();
											
										
											for (ComportamentoResposta comportamento : respostaParaAnalise.getComportamentos()) { 
											    if (comportamento.getValorComportamentoResposta().getAgiliza()!=null &&  comportamento.getValorComportamentoResposta().getAgiliza().getChecklistId() == checklist.getId()) {
											        comportamentosFiltrados.add(comportamento);
											        
											        
											    }else if(comportamento.getValorComportamentoResposta().getAgiliza()==null &&  (comportamento.getValorComportamentoResposta().getNotaTecnica()!=null || comportamento.getValorComportamentoResposta().getDiligencia()!=null ) ) {
											    	comportamentosFiltrados.add(comportamento);
											    }
											   
											}
											
											respostaParaAnalise.setComportamentos(comportamentosFiltrados);
						        
						
					}
				
					
				
			}
			
		}
		 
    return checklist;
		
		
		
	}


	@Transactional("padronizaTransactionManager")
	public Checklist vincularLinha(Integer idCheckList, Integer idLinha) {
		Checklist checklist = buscarPorId(idCheckList);
		
		Linha linha = linhaService.buscarLinha(idLinha);
		
		checklist.setLinha(linha);
		
		checklist = checklistRepository.save(checklist);
		
		linha = linhaService.atualizaLinha(linha);
		
		return checklist;
	}

	
	
	

	@Transactional("padronizaTransactionManager")
	public Checklist atualizaChecklist(Checklist checklist) {
		return checklistRepository.save(checklist);
	}
	

}
