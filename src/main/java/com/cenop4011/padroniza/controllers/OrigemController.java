package com.cenop4011.padroniza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.OrigemDTO;
import com.cenop4011.padroniza.models.Origem;
import com.cenop4011.padroniza.services.OrigemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@CrossOrigin(allowCredentials = "true",originPatterns = "*")
@RequestMapping("origens")
@Api(tags = "Origens",description = " ")
public class OrigemController {

	@Autowired
	OrigemService origemService;
	
	@PostMapping
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Origem> gravarOrigem(@RequestBody OrigemDTO origemDTO ){
		
		Origem origem = origemService.gravarOrigem(origemDTO);
		
		return ResponseEntity.ok().body(origem);
		
		
		
	}
	
}
