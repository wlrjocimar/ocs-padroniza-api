package com.cenop4011.padroniza.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.models.ComportamentoResposta;
import com.cenop4011.padroniza.services.ComportamentoRespostaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("comportamentos")
@CrossOrigin(allowCredentials = "true",originPatterns = "*")
@Api(tags = "Gerenciar comportamento do valor da resposta",description = " ")
public class ComportamentoRespostaController {
	
	
	@Autowired
	ComportamentoRespostaService comportamentoRespostaService;
	
	
	
	@PostMapping("/{idResposta}")
	
	@ApiImplicitParams({
	       @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<?> gravarAtualizarComportamento(@PathVariable Integer idResposta, @RequestBody ComportamentoRespostaDTO comportamentoRespostaDTO , HttpServletRequest req){
		
		
        
        
        ComportamentoResposta comportamentoResposta = comportamentoRespostaService.gravarValorComportamentoResposta(comportamentoRespostaDTO, idResposta);
        
        
   

        return ResponseEntity.ok().body(comportamentoResposta);
	}
	

}
