package com.cenop4011.padroniza.services;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.DiligenciaDTO;
import com.cenop4011.padroniza.exceptions.ObjectNotFoundException;
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

}
