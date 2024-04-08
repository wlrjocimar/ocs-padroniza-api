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

import com.cenop4011.padroniza.dtos.ProprietarioDTO;
import com.cenop4011.padroniza.models.Proprietario;
import com.cenop4011.padroniza.services.ProprietarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@CrossOrigin(allowCredentials = "true",originPatterns = "*")
@RequestMapping("proprietarios")
@Api(tags = "Proprietarios",description = " ")
public class ProprietarioController {
	
	
	@Autowired
	ProprietarioService proprietarioService;
	
	
	@PostMapping
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Proprietario> gravarProprietario(@RequestBody  ProprietarioDTO proprietarioDTO){
		
		Proprietario proprietario = proprietarioService.gravarProprietario(proprietarioDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(proprietario);
		
		
	}

}
