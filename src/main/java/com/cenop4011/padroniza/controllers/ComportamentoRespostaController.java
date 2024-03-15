package com.cenop4011.padroniza.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.dtos.ComportamentoRespostaDTO;
import com.cenop4011.padroniza.models.ComportamentoResposta;
import com.cenop4011.padroniza.services.ComportamentoRespostaService;

@RestController
@RequestMapping("comportamentos")
@CrossOrigin(allowCredentials = "true",originPatterns = "*")
public class ComportamentoRespostaController {
	
	
	@Autowired
	ComportamentoRespostaService comportamentoRespostaService;
	
	
	
	@PostMapping("/{idResposta}")
	public ResponseEntity<?> gravarAtualizarComportamento(@PathVariable Integer idResposta, @RequestBody ComportamentoRespostaDTO comportamentoRespostaDTO , HttpServletRequest req){
		
		
        
        
        ComportamentoResposta comportamentoResposta = comportamentoRespostaService.gravarValorComportamentoResposta(comportamentoRespostaDTO, idResposta);
        
        
   

        return ResponseEntity.ok().body(comportamentoResposta);
	}
	

}
