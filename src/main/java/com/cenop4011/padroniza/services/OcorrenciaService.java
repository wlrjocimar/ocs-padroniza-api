package com.cenop4011.padroniza.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenop4011.padroniza.dtos.OcorrenciaDTO;
import com.cenop4011.padroniza.repositories.OcorrenciaRepository;

@Service
public class OcorrenciaService {
	
	
	@Autowired
	OcorrenciaRepository ocorrenciaRepository;
	
	
	public List<OcorrenciaDTO> buscarTodasOcorrenciasSilo(){
		
		List<OcorrenciaDTO> ocorrencias = new ArrayList<>();
		
		ocorrencias= ocorrenciaRepository.buscarDados();
		
		
		
		return ocorrencias;
		
		
	}

}
