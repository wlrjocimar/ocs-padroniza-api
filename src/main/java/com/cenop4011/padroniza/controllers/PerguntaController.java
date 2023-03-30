package com.cenop4011.padroniza.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cenop4011.padroniza.models.Pergunta;

@RestController
@RequestMapping("/perguntas")
@CrossOrigin("*")
public class PerguntaController {
	
	
	@GetMapping
	//@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<?> teste(HttpServletRequest req){
		Cookie[] cookies = req.getCookies();

		// Verifica se existem cookies na requisição
		if (cookies != null) {
		    // Itera sobre o array de cookies
		    for (Cookie cookie : cookies) {
		        // Obtém o nome e o valor do cookie
		        String name = cookie.getName();
		        String value = cookie.getValue();

		        // Faz algo com o nome e o valor do cookie
		        System.out.println(name + " = " + value);
		    }
		}
		
		System.out.println("Local addrres:" + req.getLocalAddr());
		System.out.println("Remote addres addrres:" + req.getRemoteAddr());
		System.out.println("Remote Host:" + req.getRemoteHost());
		
		return ResponseEntity.ok().body("Local addrres:" + req.getLocalAddr() + " Remote addres addrres:" + req.getRemoteAddr() +  " Remote Host:" + req.getRemoteHost() + " Token user ");
		
	}
	
	
	
	@PostMapping("{idBloco}")
	public ResponseEntity<Pergunta> gravarPergunta(@PathVariable Integer idBloco , @Valid Pergunta pergunta, HttpServletRequest req){
		
		System.out.println(req.getAttribute("BBSSOToken"));
		
		
		return null;
		
	}
	
	
	
	

}
