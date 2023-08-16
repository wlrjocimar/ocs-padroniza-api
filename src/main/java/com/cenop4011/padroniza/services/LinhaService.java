package com.cenop4011.padroniza.services;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.LinhaDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.ViolacaoIntegridadeException;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.models.Linha;
import com.cenop4011.padroniza.repositories.LinhaRepository;

import ch.qos.logback.classic.util.StatusViaSLF4JLoggerFactory;

@Service
public class LinhaService {
	
	
	@Autowired
	LinhaRepository linhaRepository;
	
	
	public Linha gravarLinha(LinhaDTO linhaDTO) {
		
		
		try {
			Linha linha = new Linha();
			linha.setNomeLinha(linhaDTO.getNomeLinha());
			linha.setCodigoLinha(linhaDTO.getCodigoLinha());
			
			linha = linhaRepository.save(linha);
			return linha;
		} catch (DataIntegrityViolationException e) {
			
			throw new ViolacaoIntegridadeException("violação de integridade de chave primaria ou unica");
			
		}
		
		
		
	}


	public List<Linha> buscarTodas() {
		
		return linhaRepository.findAll();
	}


	public Linha buscarLinha(Integer codigoLinha) {
		
		Optional<Linha> linha = linhaRepository.findByCodigoLinha(codigoLinha);

		return linha.orElseThrow(()->new ObjectNotFoundException("Linha com código :" + codigoLinha +  " não localizada"));
		
	}
	
	
  @Transactional("padronizaTransactionManager")	
   public Linha buscarLinhaLazyLoad(Integer codigoLinha) {
		

		 Linha linha = linhaRepository.findByCodigoLinha(codigoLinha)
		            .orElseThrow(() -> new ObjectNotFoundException("Linha não encontrada para o codigo da linha : " + codigoLinha));
		    
		 linha.getCheckLists().size(); // Isso carrega os checklists (sem carga preguiçosa)
		    
		 for (Checklist  checklist: linha.getCheckLists()) {
			 
			 checklist.getBlocos().size();// Isso carrega os blocos (sem carga preguiçosa)
			 
			 for (Bloco bloco : checklist.getBlocos()) {
				 
				 bloco.getPerguntas().size();// Isso carrega as perguntas (sem carga preguiçosa)
			}
			
		}
		 
		    return linha;
		
	}
	

	@Transactional("padronizaTransactionManager")
	public Linha atualizaLinha(Linha linha) {
		return linhaRepository.save(linha);
	}



}
