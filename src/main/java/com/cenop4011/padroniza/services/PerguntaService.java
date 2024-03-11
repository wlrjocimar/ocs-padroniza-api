package com.cenop4011.padroniza.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.dtos.DiligenciaDTO;
import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.dtos.PerguntaInputDTO;
import com.cenop4011.padroniza.dtos.PosicaoPerguntaInputDTO;
import com.cenop4011.padroniza.dtos.RespostaDTO;
import com.cenop4011.padroniza.dtos.ValorComportamentoRespostaDTO;
import com.cenop4011.padroniza.enuns.TipoPerguntaList;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.ViolacaoIntegridadeException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.models.PerguntaHistorico;
import com.cenop4011.padroniza.models.PosicaoPergunta;
import com.cenop4011.padroniza.models.PosicaoPerguntaBlocoRecover;
import com.cenop4011.padroniza.models.PosicaoPerguntaId;
import com.cenop4011.padroniza.models.Resposta;
import com.cenop4011.padroniza.repositories.BlocoRepository;
import com.cenop4011.padroniza.repositories.OcorrenciaRepository;
import com.cenop4011.padroniza.repositories.PerguntaRepository;
import com.cenop4011.padroniza.repositories.RespostaRepository;

@Service
public class PerguntaService {
	
	
	@Autowired
	PerguntaRepository perguntaRepository;
	
	
	@Autowired
	BlocoService blocoService;
	@Autowired
	BlocoRepository blocoRepository;
	
	@Autowired
	PerguntaHistoricoService perguntaHistoricoService;
	
	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	RespostaRepository respostaRepository;

	
	
	 @Autowired
	 @Qualifier("padronizaEntityManager")
	 private EntityManager entityManager;
	 
	
	@Transactional("padronizaTransactionManager")
    public Pergunta gravarPergunta(PerguntaDTO perguntaDTO, Integer idBloco) {
		Bloco  bloco = null;
	
		
		
		
		// ao gravar uma pegunta  verificar se o  valor de comportamento é diligencia para busca o nome da ocorrencia da diligencia
		
		
				
				for (RespostaDTO respostaDTO : perguntaDTO.getRespostas()) {
					
					for (ComportamentoRespostaDTO comportamentoRespostaDTO : respostaDTO.getComportamentos()) {
						
						if(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia()!=null) {
							comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().setNomeDetalheOcorrencia(ocorrenciaRepository.buscarComplementoDiligenciaNumeroDetalheOcorrencia(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().getCodigoDetalheOcorrencia()));
							
							
						}
						
					}
					
				}
		
		
		
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

	
	@Transactional("padronizaTransactionManager")
	public Pergunta buscarPergunta(Integer idPergunta) {
		
		Optional<Pergunta> perguntaOptional = perguntaRepository.findById(idPergunta);

	    Pergunta pergunta = perguntaOptional.orElseThrow(() -> new ObjectNotFoundException("Pergunta com id :" + idPergunta + " não encontrada"));

	    // Força o carregamento da lista de blocos
	    
	    
	    if(pergunta.getBlocos()!=null ) {
	    	 pergunta.getBlocos().size();
	    }
	   

	    return pergunta;
		
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
		
	
//		if(perguntaDTO.getListaCodigosLinha().size()>0) {
//			
//			entityManager.createQuery("DELETE FROM CodigoLinha e WHERE e.pergunta = :entidade")
//            .setParameter("entidade", pergunta)
//            .executeUpdate();
//			
//		}
		
		
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
		bloco.getPerguntas();
		
		System.out.println(bloco.getPerguntas().size());
		
		
		
		List<Pergunta> copiaListaPeguntasBloco = new ArrayList<>(bloco.getPerguntas());

		for (Pergunta pergunta : copiaListaPeguntasBloco) {
		    removerPergunta(pergunta.getId(), idBloco);
		}
		
		
		
		
		
//		
//		for (PosicaoPerguntaInputDTO posicaoPerguntaInputDTO : posicoesPerguntas) {
//			
//			
//			
//			
//			Pergunta pergunta = buscarPergunta(posicaoPerguntaInputDTO.getIdPergunta());
//			
//			pergunta.getBlocos();
//			removerPergunta(pergunta.getId(), idBloco); //remover leia-se desvincular
//			
//	
//			
//		}
//		
			
		for (PosicaoPerguntaInputDTO posicaoPerguntaInputDTO : posicoesPerguntas) {
					
					
					
					
					Pergunta pergunta = buscarPergunta(posicaoPerguntaInputDTO.getIdPergunta());
					
					//pergunta.getBlocos();
					
					vincularBloco(posicaoPerguntaInputDTO.getIdPergunta(), idBloco, posicaoPerguntaInputDTO.getPosicao());
					
					
					
					
				}
		
		
		
	}


	@Transactional("padronizaTransactionManager")
	public Pergunta atualizaPerguntaSomenteParcial(PerguntaInputDTO perguntaInputDTO, Integer idPergunta, List<PosicaoPerguntaBlocoRecover> blocosVinculados) {
		
		Pergunta pergunta = buscarPergunta(idPergunta);
//		
		// antes de atualizar a pergunta garavar uma em PerguntaHistorico
		
		PerguntaHistorico perguntaHistorico = new PerguntaHistorico(pergunta);
		
		
		
		PerguntaHistorico perguntaHistoricoGravado =   perguntaHistoricoService.gravarPerguntaHistorico(perguntaHistorico);
		
		
		pergunta.setUpdatedAt(LocalDateTime.now());
		
		pergunta.setVersao(pergunta.getVersao()+1);
		
		if (perguntaInputDTO.getDescricao() != null && !perguntaInputDTO.getDescricao().isEmpty()) {
	        pergunta.setDescricao(perguntaInputDTO.getDescricao());
	    }
		
		if (perguntaInputDTO.getAutomatizavel() != null ) {
	        pergunta.setAutomatizavel(perguntaInputDTO.getAutomatizavel());
	    }

	    if (perguntaInputDTO.getVersao() != null) {
	        pergunta.setVersao(perguntaInputDTO.getVersao());
	    }

	    if (perguntaInputDTO.getAjuda() != null && !perguntaInputDTO.getAjuda().isEmpty()) {
	        pergunta.setAjuda(perguntaInputDTO.getAjuda());
	    }

	    if (perguntaInputDTO.getObservacao() != null && !perguntaInputDTO.getObservacao().isEmpty()) {
	        pergunta.setObservacao(perguntaInputDTO.getObservacao());
	    }

	    if (perguntaInputDTO.getTempoAlerta() != null) {
	        pergunta.setTempoAlerta(perguntaInputDTO.getTempoAlerta());
	    }

	   

	    if (perguntaInputDTO.getLink() != null && !perguntaInputDTO.getLink().isEmpty()) {
	        pergunta.setLink(perguntaInputDTO.getLink());
	    }
		
	    
	    if(perguntaInputDTO.getAtivo()!=null) {
	    	pergunta.setAtivo(perguntaInputDTO.getAtivo());
	    	
	    }
	    
	    if (perguntaInputDTO.getTipoResposta() != null) {
	        pergunta.setTipoResposta(perguntaInputDTO.getTipoResposta());
	    }
	    
	    
	    pergunta.getRespostas().size();
	  
	    for (Resposta resposta : pergunta.getRespostas()) {
	    	respostaRepository.delete(resposta);
			
		}
	    
	    pergunta.getRespostas().clear();
	    
	   
	    
	    
	   
	    Pergunta pergunta2 = buscarPergunta(idPergunta);
	    
	    
	    
//	    if(perguntaInputDTO.getListaCodigosLinha().size()>0) {
//	    	
//	    	pergunta.adicionarCodigosLinha(perguntaInputDTO.getListaCodigosLinha());
//	    	
//	    }
	    
	    
	    
	    
	    
	   
	    	
	    	
	    	
	    	
	    	
	    	
	    	
		
	    
	    // desvincular pergunta de todos os blocos
	    
	    for (PosicaoPerguntaBlocoRecover posicaoPerguntaBlocoRecover : blocosVinculados) {
	    	 removerPergunta(idPergunta,posicaoPerguntaBlocoRecover.getNumeroBloco());
		}
	    
	   
	    
	    for (RespostaDTO respostaDTO : perguntaInputDTO.getRespostas()) {
	    	
	    		for (ComportamentoRespostaDTO comportamentoRespostaDTO : respostaDTO.getComportamentos()) {
					
	    				if(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia()!=null) {
	    					comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().setNomeDetalheOcorrencia(ocorrenciaRepository.buscarComplementoDiligenciaNumeroDetalheOcorrencia(comportamentoRespostaDTO.getValorComportamentoResposta().getDiligencia().getCodigoDetalheOcorrencia()));
	    				}	
				}
			
		}
	    
	    
	    
	    
	    if(perguntaInputDTO.getRespostas().size()>0) {
	    	pergunta2.adicionarRespostas(perguntaInputDTO.getRespostas());
	    }
	    
	    
	  Pergunta  perguntaAtualizadaFinal = perguntaRepository.save(pergunta2);
   

	   
	  
	  perguntaAtualizadaFinal = perguntaRepository.save(pergunta2);
	      
	    perguntaAtualizadaFinal = buscarPergunta(idPergunta);
		
	    return perguntaAtualizadaFinal;
		   
	}



	public List<Pergunta> buscarPerguntasAutomatizaveis() {
		
		
		return perguntaRepository.findByAutomatizavel(true);
		
		
	}


	@Transactional("padronizaTransactionManager")
	public Pergunta vincularTodosBlocos(List<PosicaoPerguntaBlocoRecover> blocosVinculados, Integer idPergunta) {

		 Pergunta pergunta2 = buscarPergunta(idPergunta);
	    for (PosicaoPerguntaBlocoRecover posicaoPerguntaBlocoRecover : blocosVinculados) {
	    	 vincularBloco(idPergunta, posicaoPerguntaBlocoRecover.getNumeroBloco(), posicaoPerguntaBlocoRecover.getPosicaoAssumida());
		}
	    
	    return pergunta2;
	}



	
	

}
