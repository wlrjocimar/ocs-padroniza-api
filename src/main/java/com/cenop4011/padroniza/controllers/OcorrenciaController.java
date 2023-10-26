package com.cenop4011.padroniza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.OcorrenciaDTO;
import com.cenop4011.padroniza.services.OcorrenciaService;

@RestController
@RequestMapping("ocorrencias")
@CrossOrigin("*")
public class OcorrenciaController {

	
	@Autowired
	OcorrenciaService ocorrenciaService;
	
	
	@GetMapping
	public ResponseEntity<List<OcorrenciaDTO>> buscarTodasOcorrenciasSilo(){
		
		return ResponseEntity.ok().body(ocorrenciaService.buscarTodasOcorrenciasSilo());
		
		
		
	}
	
	
}
