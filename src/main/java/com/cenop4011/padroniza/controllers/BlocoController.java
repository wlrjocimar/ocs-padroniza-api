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

import com.cenop4011.padroniza.dtos.BlocoDTO;
import com.cenop4011.padroniza.models.Bloco;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.services.BlocoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;


@Api(tags = "Blocos",description = " ")
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
	
	public ResponseEntity<BlocoDTO> gravarBloco(@RequestParam(value="checklist", defaultValue = "0")  Integer idCheclist ,  @RequestBody @Validated BlocoDTO blocoDTO){
		
		System.out.println("id do checklist "+   idCheclist); // utilizar este id para vincular o bloco ao checklist
		
		Bloco blocoSalvo = (Bloco) blocoService.salvarBloco(blocoDTO,idCheclist);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(blocoSalvo.getId())
                .toUri();

       return ResponseEntity.created(location).build();
		
		

			
		
	}
	
	@GetMapping("{idBloco}")
	
	public ResponseEntity<Bloco> buscarBloco(@PathVariable Integer idBloco ){
		
		Bloco bloco = blocoService.buscarBlocoPorId(idBloco);
		
		//int qtdperguntasBloco = bloco.getPerguntas().size();
		
		return ResponseEntity.ok().body(bloco);
		
	}
	
	
	
	@PostMapping("/vincularchecklist/{idBloco}/{idCheckList}")
	 @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> vincularCheckList(@PathVariable Integer idBloco, @PathVariable Integer idCheckList){
		
		Bloco bloco = blocoService.vincularCheckList(idBloco,idCheckList);
		
		
		return ResponseEntity.ok().body(bloco);
		
	}
	
	

}
