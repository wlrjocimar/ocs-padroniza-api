package com.cenop4011.padroniza.controllers;

import java.net.URI;
import java.util.List;

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

import com.cenop4011.padroniza.dtos.ChecklistDTO;
import com.cenop4011.padroniza.models.Checklist;
import com.cenop4011.padroniza.models.Pergunta;
import com.cenop4011.padroniza.services.ChecklistService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Api(tags = "Checklists",description = " ")
@RestController
@CrossOrigin("*")
@RequestMapping("/checklists")

public class ChecklistController {
	
	@Autowired
	ChecklistService checklistService;
		
	@PostMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
})
	
	public ResponseEntity<ChecklistDTO> salvarChecklist(@RequestParam(value="codigolinha", defaultValue = "0")  Integer codigoLinha ,  @RequestBody @Validated ChecklistDTO checklistDTO){
		
		System.out.println("codigo da linha "+   codigoLinha); // utilizar este id para vincular o bloco ao checklist
		
		Checklist checklist = checklistService.gravarChecklist(checklistDTO, codigoLinha);
		URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(checklist.getId())
                .toUri();

       return ResponseEntity.created(location).build();
		
		

			
		
	}
	
	@GetMapping
	public ResponseEntity<List<Checklist>> buscarTodos(){
		
		List<Checklist> checklists = checklistService.buscarTodos();
		
		return ResponseEntity.ok().body(checklists);
		
	}
	
	
	
	@PostMapping("/vincularlinha/{idCheckList}/{idLinha}")
	 @ApiImplicitParams({
	        @ApiImplicitParam(name = "Authorization", value = "Informe o token com Bearer no inicio", required = true, dataType = "string", paramType = "header")
	})
		@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<?> vincularLinha(@PathVariable Integer idCheckList, @PathVariable Integer idLinha){
		
		Checklist checklist = checklistService.vincularLinha(idCheckList,idLinha);
		
		
		return ResponseEntity.ok().body(checklist);
		
	}
	
	
	@GetMapping("/{idChecklist}")
	public ResponseEntity<Checklist> buscarPorid(@PathVariable Integer idChecklist){
		
		Checklist checklist = checklistService.buscarPorId(idChecklist);
		
		return ResponseEntity.ok().body(checklist);
		
	}
	
	
		

}
