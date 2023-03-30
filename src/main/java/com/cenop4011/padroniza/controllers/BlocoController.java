package com.cenop4011.padroniza.controllers;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.services.BlocoService;



@RestController
@CrossOrigin("*")
@RequestMapping("/blocos")
public class BlocoController {
	
	@Autowired
	BlocoService blocoService;
	
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Bloco> salvarBloco(@RequestBody @Validated Bloco bloco){
		
		Bloco blocoSalvo = (Bloco) blocoService.salvarBloco(bloco);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(blocoSalvo.getId())
                .toUri();

       return ResponseEntity.created(location).build();
		
		

			
		
	}
	
	@GetMapping("{idBloco}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Bloco> buscarBloco(@PathVariable Integer idBloco ){
		
		Bloco bloco = blocoService.buscarBlocoPorId(idBloco);
		
		return ResponseEntity.ok().body(bloco);
		
	}
	
	
	

}
