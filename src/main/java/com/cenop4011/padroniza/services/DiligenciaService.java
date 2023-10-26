package com.cenop4011.padroniza.services;



import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cenop4011.padroniza.dtos.DiligenciaDTO;
import com.cenop4011.padroniza.models.Diligencia;
import com.cenop4011.padroniza.repositories.DiligenciaRepository;

@Service
public class DiligenciaService {
	
	@Autowired
	DiligenciaRepository diligenciaRepository;
	
	
	@Transactional("padronizaTransactionManager")
	public Diligencia gravarDiligencia(DiligenciaDTO diligenciaDTO) {
		Diligencia diligencia = new Diligencia();
				
	    BeanUtils.copyProperties(diligenciaDTO, diligencia);
		
		Diligencia diligenciaGravada = diligenciaRepository.save(diligencia);
		
		
		return diligenciaGravada;
	}


	public List<Diligencia> findAll() {
		
		return diligenciaRepository.findAll();
	}

}
