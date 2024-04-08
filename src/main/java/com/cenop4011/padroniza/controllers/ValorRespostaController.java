package com.cenop4011.padroniza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.ValorRespostaDTO;
import com.cenop4011.padroniza.models.ValorResposta;
import com.cenop4011.padroniza.services.ValorRespostaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("valoresrespostas")
@CrossOrigin(allowCredentials = "true",originPatterns = "*")
@Api(tags = "Gerenciar Valores das Respostas",description = " ")
public class ValorRespostaController {
	

	@Autowired
	ValorRespostaService valorRespostaService;
	
	
	
	@PostMapping
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<ValorResposta> gravarValorResposta(@RequestBody ValorRespostaDTO valorRespostaDTO){
		
		ValorResposta valorResposta = valorRespostaService.gravarValorResposta(valorRespostaDTO);
		
		
		return ResponseEntity.ok().body(valorResposta);
		
		
		
	}
	
	
	@GetMapping
	public ResponseEntity<List<ValorResposta>> buscarTodos(){
		
		
		List<ValorResposta> valoresRespostas = valorRespostaService.buscarTodos();
		
		
		return ResponseEntity.ok().body(valoresRespostas);
		
	}
	
	
	
	
	
}
