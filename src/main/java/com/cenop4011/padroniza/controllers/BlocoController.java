package com.cenop4011.padroniza.controllers;

import java.net.URI;

import javax.websocket.server.PathParam;

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

import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.services.BlocoService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;



@RestController
@CrossOrigin("*")
@RequestMapping("/blocos")
public class BlocoController {
	
	@Autowired
	BlocoService blocoService;
	
	
	
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	
	public ResponseEntity<Bloco> salvarBloco(@RequestParam(value="checklist", defaultValue = "0")  Integer idCheclist ,  @RequestBody @Validated Bloco bloco){
		
		System.out.println("id do checklist "+   idCheclist); // utilizar este id para vincular o bloco ao checklist
		
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
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	
	public ResponseEntity<Bloco> buscarBloco(@PathVariable Integer idBloco ){
		
		Bloco bloco = blocoService.buscarBlocoPorId(idBloco);
		
		return ResponseEntity.ok().body(bloco);
		
	}
	
	
	

}
