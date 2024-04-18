package com.cenop4011.padroniza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.AssuntoDTO;
import com.cenop4011.padroniza.models.Assunto;
import com.cenop4011.padroniza.services.AssuntoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("assuntos")
@CrossOrigin("*")
@Api(tags = "Assuntos",description = " ")
public class AssuntoController {
	
	
	@Autowired
	AssuntoService assuntoService;
	
	@PostMapping
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Assunto> gravarAssunto(@RequestBody AssuntoDTO assuntoDTO){
		
		
		Assunto assunto = assuntoService.gravarAssunto(assuntoDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(assunto);
		
	}

}
