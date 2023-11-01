package com.cenop4011.padroniza.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.DiligenciaDTO;
import com.cenop4011.padroniza.models.Diligencia;
import com.cenop4011.padroniza.services.DiligenciaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("diligencias")
@CrossOrigin("*")
@Api(tags = "Gerenciar Diligencias",description = " ")
public class DiligenciaController {
	
	@Autowired
	DiligenciaService diligenciaService;
	
	@PostMapping
	 @ApiImplicitParams({
       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Diligencia> gravarDiligencia(@Valid @RequestBody DiligenciaDTO diligenciaDTO ){
		
		Diligencia diligencia = diligenciaService.gravarDiligencia(diligenciaDTO);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(diligencia);
	}

	
	@GetMapping
	public ResponseEntity<List<DiligenciaDTO>> buscarTodasDiligencias(){
		
		List<Diligencia> diligencias = diligenciaService.findAll();
		List<DiligenciaDTO> diligenciasDTO = diligencias.stream().map(diligencia->new DiligenciaDTO(diligencia)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(diligenciasDTO);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
