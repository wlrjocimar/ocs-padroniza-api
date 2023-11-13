package com.cenop4011.padroniza.services;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.dtos.PosicaoPerguntaInputDTO;
import com.cenop4011.padroniza.exceptions.ConstraintException;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.ViolacaoIntegridadeException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.models.PosicaoPergunta;
import com.cenop4011.padroniza.models.PosicaoPerguntaId;
import com.cenop4011.padroniza.repositories.BlocoRepository;
import com.cenop4011.padroniza.repositories.PerguntaRepository;

@Service
public class PerguntaService {
	
	
	@Autowired
	PerguntaRepository perguntaRepository;
	
	
	@Autowired
	BlocoService blocoService;
	@Autowired
	BlocoRepository blocoRepository;
	
	
	 @Autowired
	 @Qualifier("padronizaEntityManager")
	 private EntityManager entityManager;
	 
	
	@Transactional("padronizaTransactionManager")
    public Pergunta gravarPergunta(PerguntaDTO perguntaDTO, Integer idBloco) {
		Bloco  bloco = null;
	
		
		
		
		
		
		Pergunta pergunta = new Pergunta(perguntaDTO);
		
		
		
		
		
		
			if(idBloco>0) {
				  bloco = blocoService.buscarBlocoPorId(idBloco);

				  pergunta.getBlocos().add(bloco);
				  perguntaRepository.save(pergunta);
				  
				  
				  bloco.getPerguntas().add(pergunta);
				  blocoRepository.save(bloco);
				  
				  
			} else {
				
				perguntaRepository.save(pergunta);
			}
			
		
		return pergunta;
		
	}



	public List<Pergunta> buscarTodas() {
		return perguntaRepository.findAll();
	}

	
	
	public Pergunta buscarPergunta(Integer idPergunta) {
		
		Optional<Pergunta> pergunta = perguntaRepository.findById(idPergunta);
		
		
		return pergunta.orElseThrow(() -> new ObjectNotFoundException("Pergunta com id :" + idPergunta +  " não encontrada"));
		
	}
	

	@Transactional("padronizaTransactionManager")
	public void removerPergunta(Integer idPergunta, Integer idBloco) {
	    Pergunta pergunta = buscarPergunta(idPergunta);
	    Bloco bloco =null;
	    PosicaoPerguntaId posicaoPerguntaId=null;
	    
	    
	    
	    if (idBloco > 0) {
	         bloco = blocoService.buscarBlocoPorId(idBloco);
	         posicaoPerguntaId = new PosicaoPerguntaId(bloco, pergunta);
	    }
	    
	   
	    
	    
	    
	    if(pergunta.getBlocos().contains(bloco)) {
	    	
	    	
	    	 if(pergunta.getPosicaoPerguntas().size()>0) {
	    		 Bloco blocoInteresse = blocoService.buscarBlocoPorId(idBloco);
	 	    	 PosicaoPergunta posicaoPerguntaDesejada = pergunta.getPosicaoPerguntas().stream()
	 	    		    .filter(posicao -> posicao.getBloco().equals(blocoInteresse))
	 	    		    .findFirst()
	 	    		    .orElse(null);
	 	    	
	 	    	pergunta.getPosicaoPerguntas().remove(posicaoPerguntaDesejada);
	 				
	 			}
	    	
	    	
	    	bloco.getPerguntas().remove(pergunta);
	        pergunta.getBlocos().remove(bloco);
	    } else if(pergunta.getBlocos().size()>0) {
	    	
	    	throw new  ObjectNotFoundException("Bloco " + idBloco + " não vinculado a pergunta");
	    }
	    
	   
	    
	    try {
	        perguntaRepository.save(pergunta);
	    } catch (DataIntegrityViolationException ex) {
	        throw new ViolacaoIntegridadeException("Não é possível excluir a pergunta porque está sendo usada em outro lugar", ex);
	    }
	}
		
		
		
		
		
		
		
		
	


	@Transactional("padronizaTransactionManager")
	public Pergunta atualizarPergunta(Integer idPergunta, @Valid PerguntaDTO perguntaDTO) {
		
		Pergunta pergunta = buscarPergunta(idPergunta);
		
	
		if(perguntaDTO.getListaCodigosLinha().size()>0) {
			
			entityManager.createQuery("DELETE FROM CodigoLinha e WHERE e.pergunta = :entidade")
            .setParameter("entidade", pergunta)
            .executeUpdate();
			
		}
		
		
		pergunta = pergunta.atualizaAtributos(perguntaDTO);
		
		pergunta = perguntaRepository.save(pergunta);
		
		return pergunta;
	
		
		
		
	}


	@Transactional("padronizaTransactionManager")
	public Pergunta vincularBloco(Integer idPergunta, Integer idBloco,Integer posicao) {
		
		try {
			Pergunta pergunta = buscarPergunta(idPergunta);
			
			Bloco bloco = blocoService.buscarBlocoPorId(idBloco);
			PosicaoPergunta posicaoPergunta = new PosicaoPergunta();
			posicaoPergunta.setPosicao(posicao);
			posicaoPergunta.setBloco(bloco);
			posicaoPergunta.setPergunta(pergunta);
			
			
			pergunta.getBlocos().add(bloco);
			pergunta.getPosicaoPerguntas().add(posicaoPergunta);
			bloco.getPerguntas().add(pergunta);
			
			pergunta = perguntaRepository.save(pergunta);
			bloco = blocoService.atualizaBloco(bloco);
			
			
			
			return pergunta;
			
		} catch (StackOverflowError e) {
			
			throw new ViolacaoIntegridadeException("violação de integridade de chave composta : " + e.toString());
		}
		
		
	}


	
	public void deletarPergunta(Integer idPergunta) {
		 Pergunta pergunta = buscarPergunta(idPergunta);
		 perguntaRepository.delete(pergunta);
		
	}



	
	@Transactional("padronizaTransactionManager")
	public void atualizarTodasPosicoes(List<PosicaoPerguntaInputDTO> posicoesPerguntas, Integer idBloco) {
		
		Bloco bloco = blocoService.buscarBlocoPorId(idBloco);
		
		//bloco.getPerguntas();
		
		for (PosicaoPerguntaInputDTO posicaoPerguntaInputDTO : posicoesPerguntas) {
			
			
			
			
			Pergunta pergunta = buscarPergunta(posicaoPerguntaInputDTO.getIdPergunta());
			
			//pergunta.getBlocos();
			
			removerPergunta(pergunta.getId(), idBloco); //remover leia-se desvincular
			
			
			
		}
		
			
		for (PosicaoPerguntaInputDTO posicaoPerguntaInputDTO : posicoesPerguntas) {
					
					
					
					
					Pergunta pergunta = buscarPergunta(posicaoPerguntaInputDTO.getIdPergunta());
					
					//pergunta.getBlocos();
					
					vincularBloco(posicaoPerguntaInputDTO.getIdPergunta(), idBloco, posicaoPerguntaInputDTO.getPosicao());
					
					
					
					
				}
		
		
		
	}
	
	

}
