package com.cenop4011.padroniza.controllers;

import java.net.URI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cenop4011.padroniza.dtos.PerguntaDTO;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.services.PerguntaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@Api(tags = "Perguntas",description = " ")
@RequestMapping("/perguntas")
@CrossOrigin("*")
public class PerguntaController {
	
	
	@Autowired
	PerguntaService perguntaService;
	
	
	
	@GetMapping
	 @ApiImplicitParams({
         @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
 })
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<?> teste(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();
		String BBSSOToken = "";

		// Verifica se existem cookies na requisição
		if (cookies != null) {
		    // Itera sobre o array de cookies
		    for (Cookie cookie : cookies) {
		        // Obtém o nome e o valor do cookie
		        String name = cookie.getName();
		        if (name.equals("BBSSOToken")) {
		        	BBSSOToken = cookie.getValue();
		        }
		        String value = cookie.getValue();
		        

		        // Faz algo com o nome e o valor do cookie
		        System.out.println(name + " = " + value);
		    }
		}
		
		System.out.println("Local addrres:" + req.getLocalAddr());
		System.out.println("Remote addres addrres:" + req.getRemoteAddr());
		System.out.println("Remote Host:" + req.getRemoteHost());
		
		return ResponseEntity.ok().body("Local addrres:" + req.getLocalAddr() + " Remote addres addrres:" + req.getRemoteAddr() +  " Remote Host:" + req.getRemoteHost() + " Meu BBSSOToken  : " + BBSSOToken);
		
	}
	
	
	
	@PostMapping
	 @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<PerguntaDTO> gravarPergunta(@RequestParam(value="bloco", defaultValue = "0")  Integer idBloco ,  @RequestBody @Validated PerguntaDTO perguntaDTO, HttpServletRequest req){
		

		Pergunta pergunta = perguntaService.gravarPergunta(perguntaDTO, idBloco);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pergunta.getId())
                .toUri();

       return ResponseEntity.created(location).build();
		
		
		
		
	}
	
	
	
	

}