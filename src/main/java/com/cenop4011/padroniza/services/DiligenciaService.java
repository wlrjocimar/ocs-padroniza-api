package com.cenop4011.padroniza.services;



import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.DiligenciaDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
import com.cenop4011.padroniza.exceptions.PersonalBadRequest;
import com.cenop4011.padroniza.models.Diligencia;
import com.cenop4011.padroniza.repositories.DiligenciaRepository;
import com.cenop4011.padroniza.repositories.OcorrenciaRepository;

@Service
public class DiligenciaService {
	
	@Autowired
	DiligenciaRepository diligenciaRepository;
	
	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	
	@Transactional("padronizaTransactionManager")
	public Diligencia gravarDiligencia(DiligenciaDTO diligenciaDTO) {
		Diligencia diligencia = new Diligencia();
				
	   diligencia.setCodigoDetalheOcorrencia(diligenciaDTO.getCodigoDetalheOcorrencia());
	   diligencia.setObservacao(diligenciaDTO.getObservacao());
	    
	    try {
	    	diligencia = ocorrenciaRepository.buscarComplementoDiligenciaNumeroDetalheOcorrencia(diligencia);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Ocorrencia não encontrada para complementar a diligencia em gravação "+ e.toString() );
		}
	    
		
		Diligencia diligenciaGravada = diligenciaRepository.save(diligencia);
		
		
		return diligenciaGravada;
	}


	public List<Diligencia> findAll() {
		
		return diligenciaRepository.findAll();
	}


	public Diligencia buscarDiligencia(Integer id) {
		
		Optional<Diligencia> diligencia = diligenciaRepository.findById(id);
		
		return diligencia.orElseThrow(()-> new ObjectNotFoundException("Diligencia não encontrada para o id : " + id));
		
	}
	
	
	public void deletarDiligencia(Integer id) {
		Diligencia diligencia = buscarDiligencia(id);
		try {
			diligenciaRepository.delete(diligencia);
		} catch (DataIntegrityViolationException e) {
			throw new PersonalBadRequest("A diligencia já está vinculada como valor de um comportamento de uma resposta");
		}
		
		
	}


	public Diligencia atualizarDiligencia(DiligenciaDTO diligenciaDTO, Integer id) {
	    Diligencia diligencia = buscarDiligencia(id);

	    if (diligenciaDTO.getAtivo() != null) {
	        diligencia.setAtivo(diligenciaDTO.getAtivo());
	    }

	    if (diligenciaDTO.getCodigoDetalheOcorrencia() != null) {
	        diligencia.setCodigoDetalheOcorrencia(diligenciaDTO.getCodigoDetalheOcorrencia());
	    }

	    if (diligenciaDTO.getObservacao() != null && !diligenciaDTO.getObservacao().isEmpty()) {
	        diligencia.setObservacao(diligenciaDTO.getObservacao());
	    }

	    if (diligenciaDTO.getNomeDetalheOcorrencia() != null && !diligenciaDTO.getNomeDetalheOcorrencia().isEmpty()) {
	        diligencia.setNomeDetalheOcorrencia(diligenciaDTO.getNomeDetalheOcorrencia());
	    }

	    Diligencia diligenciaAtualizada = diligenciaRepository.save(diligencia);

	    return diligenciaAtualizada;
	}



}
